package com.connect.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "flights")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flights {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int flightId;
    @ManyToOne
    @JoinColumn(name = "source_airport_id")
    private Airport sourceAirport;
    @ManyToOne
    @JoinColumn(name = "destination_airport_id")
    private Airport destinationAirport;
    @Column(nullable = false, unique = true, length = 10)
    private String flightNumber;
    @Embedded
    private FlightTimings flightTimings;
}