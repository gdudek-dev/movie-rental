package com.gdudek.movieRental.model.address;

import com.gdudek.movieRental.model.AbstractTimestamp;
import com.gdudek.movieRental.model.business.Staff;
import com.gdudek.movieRental.model.business.Store;
import com.gdudek.movieRental.model.customer.Customer;
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

    @Column(name = "address")
    private String address;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToMany(mappedBy = "address")
    private Set<Customer> customers = new HashSet<>();

    @OneToMany(mappedBy = "address")
    private Set<Staff>staff = new HashSet<>();

    @OneToMany(mappedBy = "address")
    private Set<Store>stores = new HashSet<>();

}
