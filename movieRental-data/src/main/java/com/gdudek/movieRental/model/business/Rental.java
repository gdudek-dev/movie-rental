package com.gdudek.movieRental.model.business;

import com.gdudek.movieRental.model.AbstractTimestamp;
import com.gdudek.movieRental.model.customer.Customer;
import com.gdudek.movieRental.model.inventory.Inventory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rental")
@Getter
@Setter
public class Rental extends AbstractTimestamp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Column(name = "rental_date")
    private LocalDateTime rentalDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @OneToMany(mappedBy = "rental")
    private Set<Payment> payments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @Override
    protected void onCreate() {
        super.onCreate();
        rentalDate=LocalDateTime.now();
        returnDate=LocalDateTime.now().plusDays(5);
    }
}
