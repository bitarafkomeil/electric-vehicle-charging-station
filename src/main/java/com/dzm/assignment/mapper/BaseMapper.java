package com.dzm.assignment.mapper;

import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <CD> - CreateDTO type parameter.
 * @param <UD> - UpdateDTO type parameter.
 * @param <D>  - DTO type parameter.
 * @param <E>  - Entity type parameter.
 */
public interface BaseMapper<CD, UD, D, E> {

    E fromCreateDTO(CD dto);

    E fromUpdateDTO(UD dto);

    D toDto(E entity);

    List<D> toDto(List<E> entityList);
}