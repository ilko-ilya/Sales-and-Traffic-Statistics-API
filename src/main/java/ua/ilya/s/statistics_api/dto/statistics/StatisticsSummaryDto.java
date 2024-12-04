package ua.ilya.s.statistics_api.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsSummaryDto {

    private int totalOrders;
    private double totalSales;
    private double averagePrice;
    private int totalPageViews;

}
