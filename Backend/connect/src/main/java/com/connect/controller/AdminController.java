package com.connect.controller;

import com.connect.entity.Airport;
import com.connect.repository.AirportRepository;
import com.connect.repository.FlightRepository;
import com.connect.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Admin")
@RequiredArgsConstructor
public class AdminController {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final AdminService adminService;

    @PostMapping("/add-airport")
    public ResponseEntity<?> addAirport(@RequestBody Airport airport){
        return adminService.addAirport(airport);
    }
    @GetMapping("/AllAirports")
    public ResponseEntity<List<Airport>> getAllAirports(){
        return adminService.getAllAirportsList();
    }
}
