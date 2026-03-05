package com.connect.entity;

import com.connect.enums.SeatStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "flightseats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int flightSeatId;

    @ManyToOne
    @JoinColumn(name = "scheduleId")
    private FlightSchedule schedule;

    @ManyToOne
    @JoinColumn(name = "seatId")
    private Seat seat;

    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;
}