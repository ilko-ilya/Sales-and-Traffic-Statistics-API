package ua.ilya.s.statistics_api.service.statistics;

import ua.ilya.s.statistics_api.dto.statistics.SalesAndTrafficByAsinDto;
import ua.ilya.s.statistics_api.dto.statistics.SalesAndTrafficByDateDto;
import ua.ilya.s.statistics_api.dto.statistics.StatisticsSummaryDto;

import java.util.List;

public interface StatisticsService {

    List<SalesAndTrafficByDateDto> getStatisticsByAsinAndDateRange(String asin, String startDate, String endDate);

    List<SalesAndTrafficByDateDto> getStatisticsByDateRange(String startDate, String endDate);

    List<SalesAndTrafficByAsinDto> getStatisticsByAsins(List<String> asins);

    StatisticsSummaryDto getTotalStatistics();

}


