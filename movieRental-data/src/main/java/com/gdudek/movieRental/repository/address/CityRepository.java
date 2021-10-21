package com.gdudek.movieRental.repository.address;


import com.gdudek.movieRental.model.address.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {

    Optional<City> findCityByNameAndCountry_Name(String name,String countryName);

    boolean existsByName(String name);

}
