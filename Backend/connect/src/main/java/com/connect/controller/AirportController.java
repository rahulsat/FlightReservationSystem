package com.connect.controller;

import com.connect.entity.Airport;
import com.connect.repository.AirportRepository;
import com.connect.repository.FlightRepository;
import com.connect.service.AirportService;
import com.connect.service.AirlineService;
import com.connect.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
@RequiredArgsConstructor
public class AirportController {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final AirportService adminService;
    private final UserService userService;
    private final AirlineService airlineService;

    @PostMapping("/add-airport")
    public ResponseEntity<?> addAirport(@RequestBody Airport airport){
        return adminService.addAirport(airport);
    }

    @GetMapping("/AllAirports")
    public ResponseEntity<List<Airport>> getAllAirports(){
        return adminService.getAllAirportsList();
    }

    @PutMapping("/updateAirport/{id}")
    public Airport updateAirport(@PathVariable Integer id,@RequestBody Airport airport) {
        return adminService.updateAirport(id, airport);
    }

    @GetMapping("/getAirport/{Id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Integer Id){
        return ResponseEntity.ok(userService.getAirportById(Id));
    }


}
