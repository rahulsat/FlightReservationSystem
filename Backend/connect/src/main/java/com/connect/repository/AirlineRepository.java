package com.connect.repository;


import com.connect.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirlineRepository extends JpaRepository<Airline, Integer> {

    Optional<Airline> findByAirlineName(String airlineName);

}
