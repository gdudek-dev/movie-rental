package com.gdudek.movieRental.repository;

import com.gdudek.movieRental.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {

    Country findCountryByName(String name);
}
