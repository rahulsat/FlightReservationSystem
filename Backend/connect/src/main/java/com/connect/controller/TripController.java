package com.connect.controller;

import com.connect.entity.Trip;
import com.connect.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Admin/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @PostMapping
    public Trip addTrip(@RequestBody Trip trip){
        return tripService.addTrip(trip);
    }

    @GetMapping
    public List<Trip> getAllTrips(){
        return tripService.getAllTrips();
    }

    @GetMapping("/{id}")
    public Optional<Trip> getTripById(@PathVariable Integer id){
        return tripService.getTripById(id);
    }

    @PutMapping("/{id}")
    public String updateTrip(@PathVariable Integer id,
                             @RequestBody Trip trip){
        return tripService.updateTrip(id, trip);
    }
}