package com.gdudek.movieRental.repository.address;

import com.gdudek.movieRental.model.address.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {

    Country findCountryByName(String name);
}
