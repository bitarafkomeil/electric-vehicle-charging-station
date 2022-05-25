package com.dzm.assignment.service;

import com.dzm.assignment.data.dto.request.NearestStationRequestDto;
import com.dzm.assignment.data.dto.station.CreateStationDto;
import com.dzm.assignment.data.dto.station.NearestStationDto;
import com.dzm.assignment.data.dto.station.StationDto;
import com.dzm.assignment.data.dto.station.UpdateStationDto;
import com.dzm.assignment.data.model.Company;
import com.dzm.assignment.data.model.Station;
import com.dzm.assignment.data.repository.CompanyRepository;
import com.dzm.assignment.data.repository.StationRepository;
import com.dzm.assignment.exception.NotFoundException;
import com.dzm.assignment.mapper.StationMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StationService extends BaseService<Station, StationRepository,
        CreateStationDto, UpdateStationDto, StationDto, StationMapper> {
    private final StationRepository stationRepository;
    private final StationMapper stationMapper;
    private final CompanyRepository companyRepository;
    public StationService(StationRepository stationRepository, StationMapper stationMapper,CompanyRepository companyRepository) {
        super(stationRepository, stationMapper);
        this.stationRepository = stationRepository;
        this.stationMapper = stationMapper;
        this.companyRepository = companyRepository;
    }

    public List<NearestStationDto> findNearestStations(NearestStationRequestDto nearestStationRequestDto) {
        List<NearestStationDto> result;
        if (!Objects.isNull(nearestStationRequestDto.getCompanyId())) {
            Company company = companyRepository.findById(nearestStationRequestDto.getCompanyId()).orElseThrow(() -> new NotFoundException("Company Id InCorrect"));
            List<Company> companies = loadAllCompanyChild(company, new ArrayList<>());
            Set<Long> companyIds = companies.stream().map(Company::getId).collect(Collectors.toSet());
            result = stationRepository.findWithDistanceAndCompanyIds(nearestStationRequestDto.getLatitude(), nearestStationRequestDto.getLongitude(), nearestStationRequestDto.getRadius(), companyIds);
        } else {
            result = stationRepository.findWithDistance(nearestStationRequestDto.getLatitude(), nearestStationRequestDto.getLongitude(), nearestStationRequestDto.getRadius());
        }

        return result;
    }
}