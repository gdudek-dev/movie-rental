package com.gdudek.movieRental.model.address;

import com.gdudek.movieRental.model.AbstractTimestamp;
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

    @OneToMany(mappedBy = "country")
    private Set<City> cities = new HashSet<>();
}
