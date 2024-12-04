package ua.ilya.s.statistics_api.mapper.statistics;

import org.mapstruct.Mapper;
import ua.ilya.s.statistics_api.dto.statistics.ReportSpecificationDto;
import ua.ilya.s.statistics_api.mapper.Mappable;
import ua.ilya.s.statistics_api.model.statistics.ReportSpecification;

@Mapper(componentModel = "spring")
public interface ReportSpecificationMapper extends Mappable<ReportSpecification, ReportSpecificationDto> {
}
