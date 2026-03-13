package com.connect.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.connect.entity.Flight;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer airlineId;

    @Column(nullable = false, length = 100)
    private String airlineName;

    @Column(nullable = false, length = 100)
    private String airlineEmail;

    @Column(nullable = false, length = 100)
    private String airlineContact;

    @Column(length = 255)
    private String airlineInfo;

    @OneToMany(mappedBy = "airline")
    private List<Flight> flight;
}
