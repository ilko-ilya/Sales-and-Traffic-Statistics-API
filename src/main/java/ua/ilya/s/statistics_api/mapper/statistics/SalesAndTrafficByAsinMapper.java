package ua.ilya.s.statistics_api.mapper.statistics;

import org.mapstruct.Mapper;
import ua.ilya.s.statistics_api.dto.statistics.SalesAndTrafficByAsinDto;
import ua.ilya.s.statistics_api.model.statistics.SalesAndTrafficByAsin;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SalesAndTrafficByAsinMapper {

    SalesAndTrafficByAsin toEntity(SalesAndTrafficByAsinDto trafficByAsinDto);

    SalesAndTrafficByAsinDto toDto(SalesAndTrafficByAsin trafficByAsin);

    List<SalesAndTrafficByAsin> toListEntity(List<SalesAndTrafficByAsinDto> trafficByAsinDtos);

    List<SalesAndTrafficByAsinDto> toListDto(List<SalesAndTrafficByAsin> trafficByAsins);
}
