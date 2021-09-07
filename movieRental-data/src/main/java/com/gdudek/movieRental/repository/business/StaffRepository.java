package com.gdudek.movieRental.repository.business;


import com.gdudek.movieRental.model.business.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff,Long> {
}
