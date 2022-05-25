package com.dzm.assignment.data.dto.station;

import lombok.Data;

@Data
public class BaseStationDto {
    private String name;
    private Double latitude;
    private Double longitude;
    private Long companyId;
}