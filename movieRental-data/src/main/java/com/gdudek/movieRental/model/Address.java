package com.gdudek.movieRental.model;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="addresses")
@Getter
@Setter
public class Address extends AbstractTimestamp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @Column(name = "adress_1")
    private String address_1;

    @Column(name = "adress_2")
    private String address_2;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "address",fetch = FetchType.LAZY)
    private Set<Customer> customers = new HashSet<>();

}
