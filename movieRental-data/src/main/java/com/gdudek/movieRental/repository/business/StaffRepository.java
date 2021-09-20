package com.gdudek.movieRental.repository.business;


import com.gdudek.movieRental.model.business.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Optional<Staff> findStaffByUsername(String username);
}
