package com.gdudek.movieRental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="countries")
@Getter
@Setter
public class Country extends AbstractTimestamp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @Column(unique=true)
    private String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "country",fetch = FetchType.LAZY)
    private Set<City> cities = new HashSet<>();
}
