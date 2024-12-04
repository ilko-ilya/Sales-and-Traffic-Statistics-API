package ua.ilya.s.statistics_api.dto.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsResponseDto {

    private ReportSpecificationDto reportSpecification;
    private List<SalesAndTrafficByDateDto> salesAndTrafficByDate;
    private List<SalesAndTrafficByAsinDto> salesAndTrafficByAsin;

}