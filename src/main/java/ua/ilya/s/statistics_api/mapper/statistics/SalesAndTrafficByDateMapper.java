package ua.ilya.s.statistics_api.mapper.statistics;

import org.mapstruct.Mapper;
import ua.ilya.s.statistics_api.dto.statistics.SalesAndTrafficByDateDto;
import ua.ilya.s.statistics_api.mapper.Mappable;
import ua.ilya.s.statistics_api.model.statistics.SalesAndTrafficByDate;

@Mapper(componentModel = "spring")
public interface SalesAndTrafficByDateMapper extends Mappable<SalesAndTrafficByDate, SalesAndTrafficByDateDto> {
}
