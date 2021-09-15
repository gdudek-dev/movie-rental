package com.gdudek.movieRental.service.address;

import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.address.Address;
import com.gdudek.movieRental.service.BasicService;

import java.util.List;


public interface AddressService extends BasicService {

     List<Address> findAllAddressesByCityName(String name) throws NotFoundException;
     List<Address> findAllAddressesByCountryName(String name);

}
