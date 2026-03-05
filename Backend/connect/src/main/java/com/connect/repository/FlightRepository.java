package com.connect.repository;

import com.connect.entity.Flights;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flights, Integer> {

}