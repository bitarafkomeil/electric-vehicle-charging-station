package com.dzm.assignment.controller;


import com.dzm.assignment.data.dto.station.CreateStationDto;
import com.dzm.assignment.data.dto.station.StationDto;
import com.dzm.assignment.data.dto.station.UpdateStationDto;
import com.dzm.assignment.service.StationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/stations")
@Slf4j
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping()
    public ResponseEntity<StationDto> createStation(@Valid @RequestBody CreateStationDto createStationDTO) {
        StationDto stationDto = stationService.create(createStationDTO);
        return ResponseEntity.ok().body(stationDto);
    }

    @PutMapping()
    public ResponseEntity<StationDto> updateStation(@Valid @RequestBody UpdateStationDto updateStationDTO) {
        StationDto stationDto = stationService.update(updateStationDTO);
        return ResponseEntity.ok().body(stationDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StationDto> getStation(@PathVariable Long id) {
        StationDto stationDto = stationService.findOne(id);
        return ResponseEntity.ok().body(stationDto);
    }

    @GetMapping()
    public ResponseEntity<Page<StationDto>> getAllStations(Pageable pageable) {
        Page<StationDto> companies = stationService.findAll(pageable);
        return ResponseEntity.ok()
                .body(companies);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable Long id) {
        stationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}