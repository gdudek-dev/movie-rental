package com.gdudek.movieRental.model.address;

import com.gdudek.movieRental.model.AbstractTimestamp;
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
public class City extends AbstractTimestamp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "city")
    private Set<Address> addresses= new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
