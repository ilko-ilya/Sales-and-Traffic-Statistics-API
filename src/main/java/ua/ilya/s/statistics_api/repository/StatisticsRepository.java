//package ua.ilya.s.statistics_api.repository;
//
//import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.mongodb.repository.Query;
//
//import java.util.Date;
//import java.util.List;
//
//public interface StatisticsRepository extends MongoRepository<Statistics, String> {
//
//    List<Statistics> findBySalesAndTrafficByAsin_ParentAsinIn(List<String> parentAsins);
//
//    List<Statistics> findStatisticsBySalesAndTrafficByAsin(String asin);
//
//    List<Statistics> findBySalesAndTrafficByDate_SalesByDate(Date startDate, Date endDate);
//
//    @Query("{ 'salesAndTrafficByAsin.parentAsin': ?0, 'salesAndTrafficByDate.date': { $gte: ?1, $lte: ?2 } }")
//    List<Statistics> findByParentAsinAndDateRange(String asin, Date startDate, Date endDate);
//
//}
