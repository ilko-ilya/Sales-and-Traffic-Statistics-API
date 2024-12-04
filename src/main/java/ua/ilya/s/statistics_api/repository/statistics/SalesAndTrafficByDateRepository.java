package ua.ilya.s.statistics_api.repository.statistics;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ua.ilya.s.statistics_api.model.statistics.SalesAndTrafficByDate;

import java.util.List;

public interface SalesAndTrafficByDateRepository extends MongoRepository<SalesAndTrafficByDate, String> {

    @Query("{ 'date': { $gte: ?0, $lte: ?1 } }")
    List<SalesAndTrafficByDate> findByDateRange(String startDate, String endDate);

}
