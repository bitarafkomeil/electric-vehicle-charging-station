package com.dzm.assignment.mapper;

import com.dzm.assignment.data.dto.station.CreateStationDto;
import com.dzm.assignment.data.dto.station.StationDto;
import com.dzm.assignment.data.dto.station.UpdateStationDto;
import com.dzm.assignment.data.model.Station;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper for {@link Station} and its DTOs.
 */
@Mapper(componentModel = "spring")
public interface StationMapper extends BaseMapper<CreateStationDto, UpdateStationDto, StationDto, Station> {

    @Mapping(source = "companyId", target = "company.id")
    @Mapping(target = "id", ignore = true)
    Station fromCreateDTO(CreateStationDto createStationDto);

    @Mapping(source = "companyId", target = "company.id")
    Station fromUpdateDTO(UpdateStationDto updateStationDto);

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "company.name", target = "companyName")
    StationDto toDto(Station entity);

    List<StationDto> toDto(List<Station> entityList);
}