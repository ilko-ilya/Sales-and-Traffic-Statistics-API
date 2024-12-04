package ua.ilya.s.statistics_api.model.statistics;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "report_specification")
public class ReportSpecification {

    @Id
    private String id;
    private String reportType;
    private ReportOptions reportOptions;
    private String dataStartTime;
    private String dataEndTime;
    private List<String> marketplaceIds;

    @Data
    public static class ReportOptions {

        private String dateGranularity;
        private String asinGranularity;

    }

}