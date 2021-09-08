package com.gdudek.movieRental.model.inventory;

import com.gdudek.movieRental.model.AbstractTimestamp;
import com.gdudek.movieRental.model.business.Rental;
import com.gdudek.movieRental.model.business.Store;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="inventory")
@Getter
@Setter
public class Inventory extends AbstractTimestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "inventory")
    private Set<Rental> rental = new HashSet<>();


}
