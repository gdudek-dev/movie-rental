package com.gdudek.movieRental.data;

import com.gdudek.movieRental.model.address.City;
import com.gdudek.movieRental.model.address.Country;
import com.gdudek.movieRental.repository.address.CityRepository;
import com.gdudek.movieRental.repository.address.CountryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class DataLoader implements CommandLineRunner {

   private final CityRepository cityRepository;
   private final CountryRepository countryRepository;

    public DataLoader(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        City city = new City();
        city.setName("xdd");
        cityRepository.save(city);
        Country country = new Country();
        country.getCities().add(city);
        city.setCountry(country);//ustawiam najpierw tate po stronie many
        countryRepository.save(country);
         cityRepository.save(city);

    }
}
