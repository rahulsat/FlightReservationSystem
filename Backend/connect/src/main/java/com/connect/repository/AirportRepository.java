package com.connect.repository;

import com.connect.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Integer> {

    Optional<Airport> findByAirportCode(String airportCode);
    List<Airport> findAll();

}