package com.connect.service;

import com.connect.entity.Airport;
import com.connect.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService {

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

    public ResponseEntity<List<Airport>> getAllAirportsList(){
        List<Airport> AllAirports=airportRepository.findAll();
        return ResponseEntity.ok(AllAirports);
    }

    public Airport updateAirport(Integer id, Airport airport) {
        Airport existing = airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found"));
        existing.setAirportName(airport.getAirportName());
        existing.setAirportCode(airport.getAirportCode());
        existing.setCity(airport.getCity());
        existing.setCountry(airport.getCountry());

        return airportRepository.save(existing);
    }
}
