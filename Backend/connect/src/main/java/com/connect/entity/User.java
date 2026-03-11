    package com.connect.entity;

    import jakarta.persistence.*;
    import lombok.*;

    import java.util.List;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    @Table(name="users")
    @Builder
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY )
        private Integer user_id;
        @Column(name="names",nullable=false,length=50)
        private String name;
        @Column(unique=true,nullable=false,length=50)
        private String email;
        @Column(nullable=false)
        private Integer age;
        @Column(nullable=false,length=120)
        private String password;
        @Column(unique = true,nullable=false,length=11)
        private String phoneNumber;
        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<Members> addMembers;
    }
