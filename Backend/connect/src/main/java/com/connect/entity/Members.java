package com.connect.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "members")
public class Members {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;

    private String name;
    private int age;

    @Column(unique = true)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;   // ✅ MUST BE EXACT NAME: user
}