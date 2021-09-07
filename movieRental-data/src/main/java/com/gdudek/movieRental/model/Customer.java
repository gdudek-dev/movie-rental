package com.gdudek.movieRental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="customers")
@Getter
@Setter
public class Customer extends AbstractTimestamp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name="username",unique = true)
    private String username;

    @Column(name="password")
    private String password;

    @Override
    protected void onCreate() {
        super.onCreate();
        created=LocalDateTime.now();
    }

}
