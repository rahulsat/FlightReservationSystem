package com.connect.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="members")
public class Members {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;
    @Column(nullable = false,length=15)
    private String name;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false,length=11,unique=true)
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
}
