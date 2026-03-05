package com.connect.repository;

import com.connect.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Integer> {

    Airport findByAirportCode(String airportCode);

}