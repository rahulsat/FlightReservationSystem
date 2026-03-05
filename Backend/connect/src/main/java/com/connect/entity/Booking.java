package com.connect.entity;

import com.connect.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "scheduleId")
    private FlightSchedule schedule;

    private LocalDate bookingDate;

    private int totalAmount;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
}