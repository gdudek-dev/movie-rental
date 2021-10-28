package com.gdudek.movieRental.model.business;

import com.gdudek.movieRental.model.AbstractUser;
import com.gdudek.movieRental.model.Role;
import com.gdudek.movieRental.model.address.Address;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "staff")
@Getter
@Setter
public class Staff extends AbstractUser implements Serializable {

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

    @ManyToMany
    @JoinTable(
            name = "staff_roles",
            joinColumns = @JoinColumn(
                    name = "staff_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "manager")
    private Set<Store>managedStores = new HashSet<>();

    @OneToMany(mappedBy = "staff")
    private Set<Payment> payments = new HashSet<>();

    @OneToMany(mappedBy = "staff")
    private Set<Rental> rentals = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Override
    public Collection<Role> getRoles(){
        return roles;
    }
}
