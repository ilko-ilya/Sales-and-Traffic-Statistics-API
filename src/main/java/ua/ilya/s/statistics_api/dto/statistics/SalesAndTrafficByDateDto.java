package ua.ilya.s.statistics_api.dto.statistics;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class SalesAndTrafficByDateDto {

    private String date;
    private SalesByDateDto salesByDate;
    private TrafficByDateDto trafficByDate;

    @Data
    public static class SalesByDateDto {

        private AmountDto orderedProductSales;
        private AmountDto orderedProductSalesB2B;
        private int unitsOrdered;
        private int unitsOrderedB2B;
        private int totalOrderItems;
        private int totalOrderItemsB2B;
        private AmountDto averageSalesPerOrderItem;
        private AmountDto averageSalesPerOrderItemB2B;
        private double averageUnitsPerOrderItem;
        private double averageUnitsPerOrderItemB2B;
        private AmountDto averageSellingPrice;
        private AmountDto averageSellingPriceB2B;
        private int unitsRefunded;
        private double refundRate;
        private int claimsGranted;
        private AmountDto claimsAmount;
        private AmountDto shippedProductSales;
        private int unitsShipped;
        private int ordersShipped;

    }

    @Data
    public static class TrafficByDateDto {

        private int browserPageViews;
        private int browserPageViewsB2B;
        private int mobileAppPageViews;
        private int mobileAppPageViewsB2B;
        private int pageViews;
        private int pageViewsB2B;
        private int browserSessions;
        private int browserSessionsB2B;
        private int mobileAppSessions;
        private int mobileAppSessionsB2B;
        private int sessions;
        private int sessionsB2B;
        private double buyBoxPercentage;
        private double buyBoxPercentageB2B;
        private double orderItemSessionPercentage;
        private double orderItemSessionPercentageB2B;
        private double unitSessionPercentage;
        private double unitSessionPercentageB2B;
        private int averageOfferCount;
        private int averageParentItems;
        private int feedbackReceived;
        private int negativeFeedbackReceived;
        private double receivedNegativeFeedbackRate;

    }

    @Data
    public static class AmountDto {

        private double amount;
        private String currencyCode;

    }
}
