package com.gdudek.movieRental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="cities")
@Getter
@Setter
public class City extends  AbstractTimestamp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "city",fetch = FetchType.LAZY)
    private Set<Address> addresses= new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
