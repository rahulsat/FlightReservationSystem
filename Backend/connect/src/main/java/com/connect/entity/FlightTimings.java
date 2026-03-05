package com.connect.entity;

import jakarta.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
public class FlightTimings {

    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private LocalTime duration;
}