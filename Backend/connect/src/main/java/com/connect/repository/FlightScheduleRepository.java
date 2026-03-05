package com.connect.repository;

import com.connect.entity.FlightSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FlightScheduleRepository extends JpaRepository<FlightSchedule, Integer> {

    List<FlightSchedule> findBySourceAirport_AirportIdAndDestinationAirport_AirportId(
            int sourceAirportId,
            int destinationAirportId
    );

}