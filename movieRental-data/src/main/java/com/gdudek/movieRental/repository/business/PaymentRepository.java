package com.gdudek.movieRental.repository.business;

import com.gdudek.movieRental.model.business.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
