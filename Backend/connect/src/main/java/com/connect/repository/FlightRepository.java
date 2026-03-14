package com.connect.repository;

import com.connect.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
        Optional<Flight> findByFlightNumber(String flightNumber);
}