package com.gdudek.movieRental.repository.address;

import com.gdudek.movieRental.model.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {


    Optional<List<Address>> findAllByCity_Name(String name);

    Optional<Address>findAddressByMainAddressAndCity_NameAndCity_Country_Name(String mainAddress,String cityName,String countryName);


    boolean existsByMainAddressAndCity_NameAndCity_Country_Name(String mainAddress,String cityName,String countryName);
}
