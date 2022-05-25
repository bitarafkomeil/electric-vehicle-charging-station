package com.dzm.assignment.data.dto.station;

public interface NearestStationDto {
    Long getId();

    Long getCompany_id();

    Double getLatitude();

    Double getLongitude();

    String getName();

    Double getDistance();
}