package ua.ilya.s.statistics_api.dto.statistics;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SalesAndTrafficByAsinDto {

    private String parentAsin;
    private String childAsin;
    private SalesByAsinDto salesByAsin;
    private TrafficByAsinDto trafficByAsin;


    @Data
    public static class SalesByAsinDto {

        private int unitsOrdered;
        private int unitsOrderedB2B;
        private AmountDto orderedProductSales;
        private AmountDto orderedProductSalesB2B;
        private int totalOrderItems;
        private int totalOrderItemsB2B;

    }

    @Data
    public static class TrafficByAsinDto {

        private int browserSessions;
        private int browserSessionsB2B;
        private int mobileAppSessions;
        private int mobileAppSessionsB2B;
        private int sessions;
        private int sessionsB2B;
        private double browserSessionPercentage;
        private double browserSessionPercentageB2B;
        private double mobileAppSessionPercentage;
        private double mobileAppSessionPercentageB2B;
        private double sessionPercentage;
        private double sessionPercentageB2B;
        private int browserPageViews;
        private int browserPageViewsB2B;
        private int mobileAppPageViews;
        private int mobileAppPageViewsB2B;
        private int pageViews;
        private int pageViewsB2B;
        private double browserPageViewsPercentage;
        private double browserPageViewsPercentageB2B;
        private double mobileAppPageViewsPercentage;
        private double mobileAppPageViewsPercentageB2B;
        private double pageViewsPercentage;
        private double pageViewsPercentageB2B;
        private double buyBoxPercentage;
        private double buyBoxPercentageB2B;
        private double unitSessionPercentage;
        private double unitSessionPercentageB2B;

    }

    @Data
    public static class AmountDto {

        private double amount;
        private String currencyCode;

    }

}