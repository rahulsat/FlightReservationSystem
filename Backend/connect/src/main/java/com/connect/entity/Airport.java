package com.connect.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "airports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int airportId;
    @Column(nullable = false, length = 100)
    private String airportName;
    @Column(nullable = false, unique = true, length = 10)
    private String airportCode;
    @Column(nullable = false, length = 50)
    private String city;
    @Column(nullable = false, length = 50)
    private String country;
    @OneToMany(mappedBy = "sourceAirport")
    private List<Flights> sourceFlights;
    @OneToMany(mappedBy = "destinationAirport")
    private List<Flights> destinationFlights;
}
