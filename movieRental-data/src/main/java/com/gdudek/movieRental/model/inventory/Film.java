package com.gdudek.movieRental.model.inventory;

import com.gdudek.movieRental.enums.MovieRating;
import com.gdudek.movieRental.model.AbstractTimestamp;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Year;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="film")
@Getter
@Setter
public class Film extends AbstractTimestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "language")
    private String language;

    @Column(name = "rental_duration")
    private int rentalDuration;

    @Column(name = "length")
    private int length;

    @Column(name="replacement_cost",precision=10, scale=2)
    private BigDecimal replacementCost;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "rating")
    private MovieRating rating;


    @OneToMany(mappedBy = "film")
    private Set<Inventory> inventory=new HashSet<>();


    @ManyToMany(mappedBy = "films")
    private Set<Category>categories = new HashSet<>();

}
