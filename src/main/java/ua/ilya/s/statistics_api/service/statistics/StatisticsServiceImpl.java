package ua.ilya.s.statistics_api.service.statistics;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.ilya.s.statistics_api.dto.statistics.SalesAndTrafficByAsinDto;
import ua.ilya.s.statistics_api.dto.statistics.SalesAndTrafficByDateDto;
import ua.ilya.s.statistics_api.dto.statistics.StatisticsSummaryDto;
import ua.ilya.s.statistics_api.mapper.statistics.SalesAndTrafficByAsinMapper;
import ua.ilya.s.statistics_api.mapper.statistics.SalesAndTrafficByDateMapper;
import ua.ilya.s.statistics_api.model.statistics.SalesAndTrafficByAsin;
import ua.ilya.s.statistics_api.model.statistics.SalesAndTrafficByDate;
import ua.ilya.s.statistics_api.repository.statistics.SalesAndTrafficByAsinRepository;
import ua.ilya.s.statistics_api.repository.statistics.SalesAndTrafficByDateRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final FileProcessingService processingService;
    private final SalesAndTrafficByAsinMapper byAsinMapper;
    private final SalesAndTrafficByDateMapper byDateMapper;
    private final SalesAndTrafficByAsinRepository salesAndTrafficByAsinRepository;
    private final SalesAndTrafficByDateRepository salesAndTrafficByDateRepository;

    @Cacheable(value = "statistics", key = "'dateRange:' + #startDate + ':' + #endDate")
    @Override
    public List<SalesAndTrafficByDateDto> getStatisticsByDateRange(String startDate, String endDate) {

        List<SalesAndTrafficByDate> salesAndTrafficByDates = salesAndTrafficByDateRepository
                .findByDateRange(startDate, endDate);
        return byDateMapper.toListDto(salesAndTrafficByDates);

    }

    @Cacheable(value = "statisticsByAsinAndDateRange",
            key = "#asin + ':' + #startDate.toString() + ':' + #endDate.toString()")
    @Override
    public List<SalesAndTrafficByDateDto> getStatisticsByAsinAndDateRange(
            String asin,
            String startDate,
            String endDate
    ) {

        boolean asinExists = !salesAndTrafficByAsinRepository
                .findByParentAsinIn(Collections.singletonList(asin)).isEmpty();
        if (!asinExists) {
            log.warn("ASIN {} не найден в базе данных!", asin);
            return Collections.emptyList();
        }

        List<SalesAndTrafficByDate> dateRecords = salesAndTrafficByDateRepository.findByDateRange(startDate, endDate);

        List<SalesAndTrafficByDateDto> result = dateRecords.stream()
                .map(byDateMapper::toDto)
                .collect(Collectors.toList());

        log.info("Найдено {} записей для ASIN {} в диапазоне {} - {}", result.size(), asin, startDate, endDate);
        return result;
    }

    @Cacheable(value = "statisticsByAsins", key = "'asins:' + #asins")
    @Override
    public List<SalesAndTrafficByAsinDto> getStatisticsByAsins(List<String> asins) {
        log.info("Finding ASINs in repository: {}", asins);

        List<SalesAndTrafficByAsin> salesAndTrafficByAsins = salesAndTrafficByAsinRepository
                .findByParentAsinIn(asins);
        log.info("Found records: {}", salesAndTrafficByAsins);
        return byAsinMapper.toListDto(salesAndTrafficByAsins);

    }

    @Cacheable(value = "totalStatistics")
    @Override
    public StatisticsSummaryDto getTotalStatistics() {

        List<SalesAndTrafficByAsin> salesAndTrafficByAsins = salesAndTrafficByAsinRepository.findAll();

        return calculateSummary(salesAndTrafficByAsins);
    }

    private StatisticsSummaryDto calculateSummary(List<SalesAndTrafficByAsin> asinList) {

        int totalOrders = asinList.stream()
                .mapToInt(asin -> asin.getSalesByAsin().getTotalOrderItems())
                .sum();

        double totalSales = asinList.stream()
                .mapToDouble(asin -> asin.getSalesByAsin().getOrderedProductSales().getAmount())
                .sum();

        double averagePrice = totalSales / (totalOrders > 0 ? totalOrders : 1);

        int totalPageViews = asinList.stream()
                .mapToInt(asin -> asin.getTrafficByAsin().getPageViews())
                .sum();

        return new StatisticsSummaryDto(totalOrders, totalSales, averagePrice, totalPageViews);
    }

    @CacheEvict(
            value = {
                    "statistics",
                    "statisticsByAsinAndDateRange",
                    "statisticsByDateRange",
                    "statisticsByAsins",
                    "totalStatistics"
            },
            allEntries = true)
    @Scheduled(fixedRate = 300000)
    public void updateStatistics() {
        log.info("Clearing all caches and updating statistics...");
        try {
            String jsonData = processingService.readJsonFile();

            processingService.processJsonData(jsonData);

            log.info("Statistics updated successfully");
        } catch (Exception e) {
            log.error("Failed to update statistics", e);
            throw new RuntimeException("Could not update statistics", e);
        }
    }
}
