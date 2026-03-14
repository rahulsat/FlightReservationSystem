package com.connect.service;

import com.connect.entity.Airline;
import com.connect.repository.AirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirlineService {
    private final AirlineRepository airlineRepository;

    public String addAirline(Airline airline){
        Optional<Airline> existing = airlineRepository.findByAirlineName(airline.getAirlineName());
        if(existing.isPresent()){
            return "Airline already exists";
        }
        airlineRepository.save(airline);
        return "Airline is Added";
    }

    public List<Airline> getAllAirlines(){
        return airlineRepository.findAll();
    }

    public String updateAirline(Integer id, Airline request) {

        Optional<Airline> existing = airlineRepository.findById(id);

        if (existing.isEmpty()) {
            return "Airline not found";
        }

        Airline airline = existing.get();

        airline.setAirlineName(request.getAirlineName());
        airline.setAirlineEmail(request.getAirlineEmail());
        airline.setAirlineContact(request.getAirlineContact());
        airline.setAirlineInfo(request.getAirlineInfo());

        airlineRepository.save(airline);

        return "Airline updated successfully";
    }
    public String patchAirline(Integer id, Airline airlineRequest){

        Optional<Airline> existing = airlineRepository.findById(id);
        if(existing.isEmpty()){
            return "Airline not found";
        }
        Airline airline = existing.get();
        if(airlineRequest.getAirlineName() != null){
            airline.setAirlineName(airlineRequest.getAirlineName());
        }
        if(airlineRequest.getAirlineContact() != null){
            airline.setAirlineContact(airlineRequest.getAirlineContact());
        }
        airlineRepository.save(airline);
        return "Airline updated partially";
    }

    public Airline getAirlineById(Integer id){
        return airlineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airline not found"));
    }
}
