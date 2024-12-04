package ua.ilya.s.statistics_api.mapper;

import java.util.List;

public interface Mappable<E, D> {

    E toEntity(D dto);
    D toDto(E entity);
    List<D> toListDto(List<E> entityList);
    List<E> toListEntity(List<D> dtoList);

}
