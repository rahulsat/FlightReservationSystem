package com.connect.entity;

import jakarta.persistence.*;
import com.connect.entity.Flight;
import java.time.LocalTime;

public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tripId;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "source_airport_id")
    private Airport sourceAirport;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id")
    private Airport destinationAirport;

    private LocalTime departureTime;
    private LocalTime arrivalTime;

    private int tripNumber;
}