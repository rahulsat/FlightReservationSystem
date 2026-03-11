package com.connect.controller;

import com.connect.entity.Airport;
import com.connect.repository.AirportRepository;
import com.connect.repository.FlightRepository;
import com.connect.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
