package com.gdudek.movieRental.repository.address;

import com.gdudek.movieRental.model.address.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {

    Optional<City> findByName(String name);
    List<City> findByCountry_Name(String name);
}
