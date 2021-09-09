package com.gdudek.movieRental.repository.business;


import com.gdudek.movieRental.model.business.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
}
