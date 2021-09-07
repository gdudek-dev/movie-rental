package com.gdudek.movieRental.repository.business;


import com.gdudek.movieRental.model.business.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental,Long> {
}
