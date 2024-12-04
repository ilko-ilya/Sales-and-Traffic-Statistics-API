package ua.ilya.s.statistics_api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.ilya.s.statistics_api.dto.statistics.SalesAndTrafficByAsinDto;
import ua.ilya.s.statistics_api.dto.statistics.SalesAndTrafficByDateDto;
import ua.ilya.s.statistics_api.dto.statistics.StatisticsSummaryDto;
import ua.ilya.s.statistics_api.service.statistics.StatisticsService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/date-range")
    public ResponseEntity<List<SalesAndTrafficByDateDto>> getStatisticsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        List<SalesAndTrafficByDateDto> stats = statisticsService.getStatisticsByDateRange(startDate, endDate);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/asin-date-range")
    public ResponseEntity<List<SalesAndTrafficByDateDto>> getStatisticsByAsinAndDateRange(
            @RequestParam String asin,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        List<SalesAndTrafficByDateDto> stats = statisticsService
                .getStatisticsByAsinAndDateRange(asin, startDate, endDate);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/asins")
    public ResponseEntity<List<SalesAndTrafficByAsinDto>> getStatisticsByAsins(
            @RequestParam List<String> asins) {

        log.info("Received ASINs: {}", asins);

        List<SalesAndTrafficByAsinDto> stats = statisticsService.getStatisticsByAsins(asins);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/total")
    public ResponseEntity<StatisticsSummaryDto> getTotalStatistics() {
        log.info("Request received for /total endpoint");
        StatisticsSummaryDto totalStats = statisticsService.getTotalStatistics();
        log.info("Returning total statistics: {}", totalStats);
        return ResponseEntity.ok(totalStats);
    }

}
