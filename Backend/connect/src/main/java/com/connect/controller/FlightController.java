package com.connect.controller;

import com.connect.entity.Flight;
import com.connect.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Admin")
public class FlightController {
    private final FlightService flightService;

    @PostMapping("/AddFlights")
    public ResponseEntity<?> addFlights(@RequestBody Flight flight){
        return ResponseEntity.ok(flightService.addFlight(flight));
    }

    @GetMapping("/getAllFlights")
    public ResponseEntity<List<Flight>> getAllFlights(){
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @PutMapping("/updateFlight/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Integer id,
                                               @RequestBody Flight flight){
        return ResponseEntity.ok(flightService.updateFlight(id, flight));
    }

    @PatchMapping("/updateFlight/{id}")
    public ResponseEntity<Flight> patchFlight(@PathVariable Integer id,
                                              @RequestBody Flight flight){
        return ResponseEntity.ok(flightService.patchFlight(id, flight));
    }

    @GetMapping("/getFlight/{Id}")
    public ResponseEntity<Flight> getFlightByid(@PathVariable Integer Id){
        return ResponseEntity.ok(flightService.getFlightById(Id));
    }

    @GetMapping("/search")
    public List<Flight> searchFlights(
            @RequestParam String source,
            @RequestParam String destination,
            @RequestParam(required = false) String date
    ) {
        return flightService.searchFlights(source, destination);
    }

    // 2️⃣ Flights by airport
    @GetMapping("/airport/{airportCode}")
    public List<Flight> getFlightsByAirport(
            @PathVariable String airportCode
    ) {
        return flightService.getFlightsByAirport(airportCode);
    }

    // 3️⃣ Flights by airline
    @GetMapping("/airline/{airlineId}")
    public List<Flight> getFlightsByAirline(
            @PathVariable Integer airlineId
    ) {
        return flightService.getFlightsByAirline(airlineId);
    }
}
