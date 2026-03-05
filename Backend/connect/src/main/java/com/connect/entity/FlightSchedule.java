package com.connect.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "flightschedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleId;

    @ManyToOne
    @JoinColumn(name = "flightid")
    private Flights flight;

    @ManyToOne
    @JoinColumn(name = "source_airport_id")
    private Airport sourceAirport;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id")
    private Airport destinationAirport;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int availableSeats;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    private LocalTime duration;
}