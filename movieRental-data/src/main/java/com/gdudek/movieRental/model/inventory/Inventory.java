package com.gdudek.movieRental.model.inventory;

import com.gdudek.movieRental.model.AbstractTimestamp;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


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


}
