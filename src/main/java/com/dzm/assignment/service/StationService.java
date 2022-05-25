package com.dzm.assignment.service;

import com.dzm.assignment.data.dto.station.CreateStationDto;
import com.dzm.assignment.data.dto.station.StationDto;
import com.dzm.assignment.data.dto.station.UpdateStationDto;
import com.dzm.assignment.data.model.Station;
import com.dzm.assignment.data.repository.StationRepository;
import com.dzm.assignment.mapper.StationMapper;
import org.springframework.stereotype.Service;

@Service
public class StationService extends BaseService<Station, StationRepository,
        CreateStationDto, UpdateStationDto, StationDto, StationMapper> {

    public StationService(StationRepository stationRepository, StationMapper stationMapper) {
        super(stationRepository, stationMapper);
    }
}