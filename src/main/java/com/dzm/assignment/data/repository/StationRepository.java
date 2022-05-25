package com.dzm.assignment.data.repository;

import com.dzm.assignment.data.dto.station.NearestStationDto;
import com.dzm.assignment.data.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    @Query(value = "select * from (SELECT * , SQRT( " +
            "    POW(111 * (latitude - :latitude), 2) + " +
            "    POW(111 * (:longitude - longitude) * COS(latitude / 57.3), 2)) AS distance " +
            "FROM station ORDER BY distance) al " +
            "where distance < :radius " +
            "ORDER BY distance", nativeQuery = true)
    List<NearestStationDto> findWithDistance(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("radius") Double radius);

    @Query(value = "select * from (SELECT * , SQRT( " +
            "    POW(111 * (latitude - :latitude), 2) + " +
            "    POW(111 * (:longitude - longitude) * COS(latitude / 57.3), 2)) AS distance " +
            "FROM station where company_id in (:companyIds) ORDER BY distance) al " +
            "where distance < :radius " +
            "ORDER BY distance", nativeQuery = true)
    List<NearestStationDto> findWithDistanceAndCompanyIds(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("radius") Double radius, @Param("companyIds") Set<Long> companyIds);
}