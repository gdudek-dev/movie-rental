package com.gdudek.movieRental.repository.business;

import com.gdudek.movieRental.model.business.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
