package com.gdudek.movieRental.service.address.impl;

import com.gdudek.movieRental.exception.AlreadyExistException;
import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.address.Address;
import com.gdudek.movieRental.model.address.City;
import com.gdudek.movieRental.model.address.Country;
import com.gdudek.movieRental.repository.address.AddressRepository;
import com.gdudek.movieRental.repository.address.CityRepository;
import com.gdudek.movieRental.repository.address.CountryRepository;
import com.gdudek.movieRental.service.address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService  {

    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;


    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address findById(Long id) throws NotFoundException {

        return addressRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Address with id "+ id + " not found"));
    }

    @Override
    @Transactional
    public Address save(Object addressToSave) throws AlreadyExistException {
       Address address = (Address) addressToSave;
       City city = address.getCity();
       Country country = city.getCountry();

      if(addressRepository.existsByMainAddressAndPostalCode(address.getMainAddress(), address.getPostalCode())
              &&cityRepository.existsByName(city.getName())
              &&countryRepository.existsByName(country.getName())) {
            throw new AlreadyExistException("Address "+address.getMainAddress()
                    +" with postal code in selected country and city "
                    +address.getPostalCode()
                    +" already exist");
        }

        if(!countryRepository.existsByName(country.getName()))
        {
            country.getCities().add(city);
            countryRepository.save(country);
            city.getAddresses().add(address);
            cityRepository.save(city);
            return addressRepository.save(address);
        }
        if(!cityRepository.existsByName(city.getName()))
        {
            countryRepository.findCountryByName(country.getName()).get().getCities().add(city);
            city.getAddresses().add(address);
            city.setCountry(countryRepository.findCountryByName(country.getName()).orElseThrow());
            cityRepository.save(city);
            return addressRepository.save(address);
        }

        cityRepository.findCityByName(city.getName()).get().getAddresses().add(address);
        address.setCity(cityRepository.findCityByName(city.getName()).orElseThrow());
        return addressRepository.save(address);
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NotFoundException {

        if(addressRepository.existsById(id)){
            Address addressToDelete = addressRepository.getById(id);
            addressToDelete.getCity().getAddresses().remove(addressToDelete);
            addressRepository.deleteById(id);
        }
        else{
            throw new NotFoundException("Address with id "+ id + " not found");
        }

    }

    @Override
    public List<Address> findAllAddressesByCityName(String name) throws NotFoundException {
        return addressRepository.findAllByCity_Name(name)
                .orElseThrow(()->new NotFoundException("Addresses with name "+ name + " not found"));
    }

    @Override
    public List<Address> findAllAddressesByCountryName(String name) {
        Country country = countryRepository.findCountryByName(name).orElseThrow();
        List<City> cities = new ArrayList<>(country.getCities());
        List<Address> addresses = new ArrayList<>();
        cities.forEach(city -> addresses.addAll(city.getAddresses()));
        return addresses;
    }
}
