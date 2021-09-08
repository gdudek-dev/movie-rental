package com.gdudek.movieRental.model.business;

import com.gdudek.movieRental.model.AbstractTimestamp;
import com.gdudek.movieRental.model.address.Address;
import com.gdudek.movieRental.model.customer.Customer;
import com.gdudek.movieRental.model.inventory.Inventory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "stores")
@Getter
@Setter
public class Store extends AbstractTimestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @OneToMany(mappedBy = "store")
    private Set<Staff> staff = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="manager_id")
    private Staff manager;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "store")
    private Set<Customer> customers = new HashSet<>();

    @OneToMany(mappedBy = "store")
    private Set<Inventory>inventories = new HashSet<>();



}
