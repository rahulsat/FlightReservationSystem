package com.connect.controller;

import com.connect.entity.Airline;
import com.connect.entity.Airport;
import com.connect.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/Admin")
@RestController
@RequiredArgsConstructor
public class AirlineController {
    private final AirlineService airlineService;

    @PostMapping("/add-airline")
    public ResponseEntity<?> addAirline(@RequestBody Airline airline){
          return ResponseEntity.ok(airlineService.addAirline(airline));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Airline>> getAllAirports(){
        return ResponseEntity.ok(airlineService.getAllAirlines());
    }
    @PutMapping("/airlines/{id}")
    public ResponseEntity<String> updateAirline(@PathVariable Integer id, @RequestBody Airline airline){
        return ResponseEntity.ok(airlineService.updateAirline(Math.toIntExact(id), airline));
    }
    @PatchMapping("/airlines/{id}")
    public ResponseEntity<String> patchAirline(@PathVariable Integer id, @RequestBody Airline airline){
        return ResponseEntity.ok(airlineService.patchAirline(id, airline));
    }

    @GetMapping("/getAirline/{Id}")
    public ResponseEntity<Airline> getAirlineById(@PathVariable Integer Id){
        return ResponseEntity.ok(airlineService.getAirlineById(Id));
    }

}
