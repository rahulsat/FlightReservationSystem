package com.connect.service;

import com.connect.entity.Airport;
import com.connect.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AirportRepository airportRepository;

    public ResponseEntity<?> addAirport(Airport airportRequest){
        Optional<Airport> airport=airportRepository.findByAirportCode(airportRequest.getAirportCode());
        if(airport.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Airport already registered");
        }
        Airport savedAirport = airportRepository.save(airportRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedAirport);
    }
}
