package com.dzm.assignment.data.dto.station;

import lombok.Data;

@Data
public class StationDto extends BaseStationDto {
    private Long id;
    private String companyName;
    private Double distance;
}