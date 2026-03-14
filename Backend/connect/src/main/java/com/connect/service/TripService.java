package com.connect.service;

import com.connect.entity.Airport;
import com.connect.entity.Flight;
import com.connect.entity.Trip;
import com.connect.repository.AirportRepository;
import com.connect.repository.FlightRepository;
import com.connect.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public Trip addTrip(Trip request){

        Flight flight = flightRepository.findById(request.getFlight().getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        Airport source = airportRepository.findById(request.getSourceAirport().getAirportId())
                .orElseThrow(() -> new RuntimeException("Source airport not found"));

        Airport destination = airportRepository.findById(request.getDestinationAirport().getAirportId())
                .orElseThrow(() -> new RuntimeException("Destination airport not found"));

        Trip trip = new Trip();
        trip.setFlight(flight);
        trip.setSourceAirport(source);
        trip.setDestinationAirport(destination);
        trip.setDepartureTime(request.getDepartureTime());
        trip.setArrivalTime(request.getArrivalTime());
        trip.setTripNumber(request.getTripNumber());

        return tripRepository.save(trip);
    }

    public List<Trip> getAllTrips(){
        return tripRepository.findAll();
    }

    public Optional<Trip> getTripById(Integer id){
        return tripRepository.findById(id);
    }

    public String updateTrip(Integer id, Trip request){

        Optional<Trip> existing = tripRepository.findById(id);

        if(existing.isEmpty()){
            return "Trip not found";
        }

        Trip trip = existing.get();

        trip.setFlight(request.getFlight());
        trip.setSourceAirport(request.getSourceAirport());
        trip.setDestinationAirport(request.getDestinationAirport());
        trip.setDepartureTime(request.getDepartureTime());
        trip.setArrivalTime(request.getArrivalTime());
        trip.setTripNumber(request.getTripNumber());

        tripRepository.save(trip);

        return "Trip updated successfully";
    }
}