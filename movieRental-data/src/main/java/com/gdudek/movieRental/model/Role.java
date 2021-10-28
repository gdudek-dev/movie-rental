package com.gdudek.movieRental.model;

import com.gdudek.movieRental.enums.RoleType;
import com.gdudek.movieRental.model.business.Staff;
import com.gdudek.movieRental.model.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private RoleType roleType;

    @ManyToMany(mappedBy = "roles")
    private Collection<Customer> customers;

    @ManyToMany(mappedBy = "roles")
    private Collection<Staff> staff;
}
