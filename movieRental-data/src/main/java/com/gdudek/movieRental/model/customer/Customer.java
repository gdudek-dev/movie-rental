package com.gdudek.movieRental.model.customer;

import com.gdudek.movieRental.model.AbstractUser;
import com.gdudek.movieRental.model.Role;
import com.gdudek.movieRental.model.address.Address;
import com.gdudek.movieRental.model.business.Payment;
import com.gdudek.movieRental.model.business.Rental;
import com.gdudek.movieRental.model.business.Store;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="customers")
@Getter
@Setter
public class Customer extends AbstractUser implements Serializable {

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

    @Column(name = "created")
    private LocalDateTime created;


    @ManyToMany
    @JoinTable(
            name = "customer_roles",
            joinColumns = @JoinColumn(
                    name = "customer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "customer")
    private Set<Payment> payments = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private Set<Rental> rentals = new HashSet<>();


    @Override
    protected void onCreate() {
        super.onCreate();
        created= LocalDateTime.now();
    }

    @Override
    public Collection<Role> getRoles(){
        return roles;
    }

}
