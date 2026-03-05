package com.connect.repository;

import com.connect.entity.FlightSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FlightSeatRepository extends JpaRepository<FlightSeat, Integer> {

    List<FlightSeat> findBySchedule_ScheduleId(int scheduleId);

}