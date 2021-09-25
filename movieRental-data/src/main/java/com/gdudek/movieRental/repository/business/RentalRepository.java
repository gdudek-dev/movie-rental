package com.gdudek.movieRental.repository.business;


import com.gdudek.movieRental.model.business.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental,Long> {

   Optional<List<Rental>> findAllByCustomer_Id(Long id);
}
