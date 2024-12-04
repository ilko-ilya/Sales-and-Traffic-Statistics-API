package ua.ilya.s.statistics_api.repository.statistics;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ua.ilya.s.statistics_api.model.statistics.ReportSpecification;

import java.util.List;

public interface ReportSpecificationRepository extends MongoRepository<ReportSpecification, String> {

    @Query("{ 'dataStartTime': { $gte: ?0 }, 'dataEndTime': { $lte: ?1 } }")
    List<ReportSpecification> findByDateRange(String startDate, String endDate);

}
