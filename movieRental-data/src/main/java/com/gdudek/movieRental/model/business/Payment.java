package com.gdudek.movieRental.model.business;

import com.gdudek.movieRental.model.AbstractTimestamp;
import com.gdudek.movieRental.model.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment extends AbstractTimestamp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;

    @Column(name="amount",precision=10, scale=2)
    private BigDecimal amount;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @OneToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Override
    protected void onCreate() {
        super.onCreate();
        paymentDate=LocalDateTime.now();
    }
}
