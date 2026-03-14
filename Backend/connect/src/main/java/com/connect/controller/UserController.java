package com.connect.controller;

import com.connect.entity.Airline;
import com.connect.entity.Airport;
import com.connect.entity.Flight;
import com.connect.entity.User;
import com.connect.service.AirlineService;
import com.connect.service.FlightService;
import com.connect.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final FlightService flightService;
    private final AirlineService airlineService;

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable int id,@RequestBody User request){
        return ResponseEntity.ok(userService.patchUser(id,request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User request) {
        return userService.putUser(id, request);
    }

    @GetMapping("/AllAirports")
    public ResponseEntity<List<Airport>> getAirports(){
        return userService.getAllAirportsList();
    }

    @GetMapping("/getAllFlights")
    public ResponseEntity<List<Flight>> getAllFlights(){
        return ResponseEntity.ok(flightService.getAllFlights());
    }


    @GetMapping("/getFlight/{Id}")
    public ResponseEntity<Flight> getFlightByid(@PathVariable Integer Id){
        return ResponseEntity.ok(flightService.getFlightById(Id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Airline>> getAllAirlines(){
        return ResponseEntity.ok(airlineService.getAllAirlines());
    }

    @GetMapping("/getAirline/{Id}")
    public ResponseEntity<Airline> getAirlineById(@PathVariable Integer Id){
        return ResponseEntity.ok(airlineService.getAirlineById(Id));
    }

    @GetMapping("/getAirport/{Id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Integer Id){
        return ResponseEntity.ok(userService.getAirportById(Id));
    }

}
