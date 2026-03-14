package com.connect.repository;

import com.connect.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
        Optional<Flight> findByFlightNumber(String flightNumber);

        List<Flight> findByAirline_AirlineId(Integer airlineId);

        // Flights by airport code
        @Query("""
            SELECT f FROM Flight f
            WHERE f.sourceAirport.airportCode = :airportCode
               OR f.destinationAirport.airportCode = :airportCode
           """)
        List<Flight> findFlightsByAirportCode(String airportCode);

        // Search flights
        @Query("""
            SELECT f FROM Flight f
            WHERE f.sourceAirport.airportCode = :source
              AND f.destinationAirport.airportCode = :destination
           """)
        List<Flight> searchFlights(String source, String destination);
}