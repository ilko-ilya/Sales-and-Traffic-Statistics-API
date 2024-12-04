package ua.ilya.s.statistics_api.repository.statistics;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ua.ilya.s.statistics_api.model.statistics.SalesAndTrafficByAsin;

import java.util.List;

public interface SalesAndTrafficByAsinRepository extends MongoRepository<SalesAndTrafficByAsin, String> {

    @Query("{ 'parentAsin': { $in: ?0 } }")
    List<SalesAndTrafficByAsin> findByParentAsinIn(List<String> parentAsins);

}
