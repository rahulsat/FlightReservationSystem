package com.connect.entity;

import com.connect.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;

    @OneToOne
    @JoinColumn(name = "bookingId")
    private Booking booking;

    @Column(nullable = false)
    private int amount;

    @Enumerated(EnumType.STRING)
    private BookingStatus paymentStatus;

    private LocalDateTime paymentTime;

    @Column(unique = true)
    private String transactionId;
}