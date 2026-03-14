package com.connect.service;

import com.connect.entity.Flight;
import com.connect.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;

    public String addFlight(Flight flight) {

        Optional<Flight> existingFlight = flightRepository.findByFlightNumber(flight.getFlightNumber());
        if (existingFlight.isPresent()) {
            return "Flight Already Present.";
        }
        flightRepository.save(flight);
        return "Flight Added Successfully.";
    }

    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }

    public Flight updateFlight(Integer id, Flight flight){

        Flight existingFlight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        existingFlight.setFlightNumber(flight.getFlightNumber());
        existingFlight.setAirline(flight.getAirline());
        existingFlight.setSourceAirport(flight.getSourceAirport());
        existingFlight.setDestinationAirport(flight.getDestinationAirport());

        return flightRepository.save(existingFlight);
    }

    public Flight patchFlight(Integer id, Flight flight){

        Flight existingFlight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
        if(flight.getFlightNumber() != null){
            existingFlight.setFlightNumber(flight.getFlightNumber());
        }
        if(flight.getAirline() != null){
            existingFlight.setAirline(flight.getAirline());
        }
        if(flight.getSourceAirport() != null){
            existingFlight.setSourceAirport(flight.getSourceAirport());
        }
        if(flight.getDestinationAirport() != null){
            existingFlight.setDestinationAirport(flight.getDestinationAirport());
        }

        return flightRepository.save(existingFlight);
    }

    public Flight getFlightById(Integer Id){
        return flightRepository.findById(Id)
                .orElseThrow(()-> new RuntimeException("Flight with Id Not existing"));
    }

}
