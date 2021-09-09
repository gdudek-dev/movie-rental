package com.gdudek.movieRental.repository.business;


import com.gdudek.movieRental.model.business.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {
}
