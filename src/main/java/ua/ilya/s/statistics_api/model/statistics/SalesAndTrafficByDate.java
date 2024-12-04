package ua.ilya.s.statistics_api.model.statistics;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "sales_and_traffic_by_date")
public class SalesAndTrafficByDate {
    @Id
    private String id;
    private String date;
    private SalesByDate salesByDate;
    private TrafficByDate trafficByDate;

    @Data
    public static class SalesByDate {

        private Amount orderedProductSales;
        private Amount orderedProductSalesB2B;
        private int unitsOrdered;
        private int unitsOrderedB2B;
        private int totalOrderItems;
        private int totalOrderItemsB2B;
        private Amount averageSalesPerOrderItem;
        private Amount averageSalesPerOrderItemB2B;
        private double averageUnitsPerOrderItem;
        private double averageUnitsPerOrderItemB2B;
        private Amount averageSellingPrice;
        private Amount averageSellingPriceB2B;
        private int unitsRefunded;
        private double refundRate;
        private int claimsGranted;
        private Amount claimsAmount;
        private Amount shippedProductSales;
        private int unitsShipped;
        private int ordersShipped;

    }

    @Data
    public static class TrafficByDate {

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
    public static class Amount {

        private double amount;
        private String currencyCode;

    }

}