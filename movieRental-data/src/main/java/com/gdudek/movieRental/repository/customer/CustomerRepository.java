package com.gdudek.movieRental.repository.customer;

import com.gdudek.movieRental.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<Customer> findCustomerByUsername(String username);
}
