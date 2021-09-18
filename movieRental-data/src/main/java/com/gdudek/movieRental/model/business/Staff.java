package com.gdudek.movieRental.model.business;

import com.gdudek.movieRental.model.AbstractTimestamp;
import com.gdudek.movieRental.model.address.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "staff")
@Getter
@Setter
public class Staff extends AbstractTimestamp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "email")
    String email;

    @Column(name = "username",unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "manager")
    private Set<Store>stores = new HashSet<>();

    @OneToMany(mappedBy = "staff")
    private Set<Payment> payments = new HashSet<>();

    @OneToMany(mappedBy = "staff")
    private Set<Rental> rentals = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
