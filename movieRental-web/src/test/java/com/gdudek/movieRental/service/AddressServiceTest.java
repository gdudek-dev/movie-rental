package com.gdudek.movieRental.service;


import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.address.Address;
import com.gdudek.movieRental.model.address.City;
import com.gdudek.movieRental.model.address.Country;
import com.gdudek.movieRental.repository.address.AddressRepository;
import com.gdudek.movieRental.repository.address.CityRepository;
import com.gdudek.movieRental.repository.address.CountryRepository;
import com.gdudek.movieRental.service.address.impl.AddressServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
class AddressServiceTest {

    @Mock
    AddressRepository addressRepository;

    @Mock
    CountryRepository countryRepository;

    @Mock
    CityRepository cityRepository;

    @InjectMocks
    AddressServiceImpl addressService;

    Country country;
    City city1;
    City city2;
    Address address1;
    Address address2;
    List<Address>addresses = new ArrayList<>();

    @BeforeEach
    void setup()
    {
        MockitoAnnotations.initMocks(this);

        country = new Country();
        country.setName("Poland");

        city1 = new City();
        city1.setName("Warsaw");

        city2 = new City();
        city2.setName("Gdańsk");

        address1 = new Address();
        address1.setId(1L);
        address1.setMainAddress("Wola 24a");
        address1.setPostalCode("32-133");

        address2 = new Address();
        address2.setId(2L);
        address2.setMainAddress("John 11a");

        country.getCities().add(city1);
        country.getCities().add(city2);
        city1.setCountry(country);
        city2.setCountry(country);

        city1.getAddresses().add(address1);
        address1.setCity(city1);

        city2.getAddresses().add(address2);
        address2.setCity(city2);

        addresses.add(address1);
        addresses.add(address2);


    }

    @Test
    void shouldReturnAllAddressesByCityName() throws NotFoundException {

        //given
        when(addressRepository.findAllByCity_Name(any(String.class))).thenReturn(Optional.of(List.of(address1)));
        //when
        List<Address> addressesByCityName = addressService.findAllAddressesByCityName("Warsaw");
        //then
        assertThat(addressesByCityName).containsExactly(address1);
    }

    @Test
    void shouldReturnAllAddressesByCountryName() {

        //given
        when(countryRepository.findCountryByName(any(String.class))).thenReturn(Optional.of(country));
        //when
        List<Address>addressesByCountryName = addressService.findAllAddressesByCountryName("Poland");
        //then
        assertThat(addressesByCountryName).containsExactlyInAnyOrderElementsOf(addresses);
    }

    @Test
    void shouldAddAddress()  {
        //given
        when(addressRepository.save(any(Address.class))).thenReturn(address1);
        when(cityRepository.save(any(City.class))).thenReturn(city1);
        when(countryRepository.save(any(Country.class))).thenReturn(country);
        //when
        Address addedAddress = addressService.save(address1);
        //then
        assertThat(addedAddress).isEqualTo(address1);
    }
    @Test
    void shouldAddWhenCountryExistButCityNot() {
        //given
        when(addressRepository.save(any(Address.class))).thenReturn(address1);
        when(cityRepository.save(any(City.class))).thenReturn(city1);
        when(countryRepository.findCountryByName(any(String.class))).thenReturn(Optional.of(country));
        when(countryRepository.existsByName(any(String.class))).thenReturn(true);
        //when
        Address addedAddress = addressService.save(address1);
        //then
        assertThat(addedAddress).isEqualTo(address1);
    }

    @Test
    void shouldAddWhenCountryAndCityExist()  {
        //given
        when(addressRepository.save(any(Address.class))).thenReturn(address1);
        when(cityRepository.existsByName(any(String.class))).thenReturn(true);
        when(countryRepository.existsByName(any(String.class))).thenReturn(true);
        when(cityRepository.findCityByNameAndCountry_Name(any(String.class),any(String.class))).thenReturn(Optional.of(city1));
        //when
        Address addedAddress = addressService.save(address1);
        //then
        assertThat(addedAddress).isEqualTo(address1);
    }

    @Test
    void shouldDeleteAddress() throws NotFoundException {
        // given
        when(addressRepository.existsById(1L)).thenReturn(true);
        when(addressRepository.getById(any(Long.class))).thenReturn(address1);
        // when
        addressService.deleteById(1L);
        // then
        verify(addressRepository).deleteById(1L);
    }

    @Test
    void shouldNotDeleteAddressWhenNotFound()  {
        // given
        // when
        // then
        assertThatThrownBy(()->addressService.deleteById(1L)).isInstanceOf(NotFoundException.class);
    }
}