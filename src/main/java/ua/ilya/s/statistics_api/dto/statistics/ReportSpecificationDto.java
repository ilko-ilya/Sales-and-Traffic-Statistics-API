package ua.ilya.s.statistics_api.dto.statistics;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ReportSpecificationDto {

    private String reportType;
    private ReportOptionsDto reportOptions;
    private String dataStartTime;
    private String dataEndTime;
    private List<String> marketplaceIds;

    @Data
    public static class ReportOptionsDto {
        private String dateGranularity;
        private String asinGranularity;
    }

}