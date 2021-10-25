package com.gdudek.movieRental.service.customer.impl;

import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.address.Address;
import com.gdudek.movieRental.model.customer.Customer;
import com.gdudek.movieRental.repository.address.AddressRepository;
import com.gdudek.movieRental.repository.customer.CustomerRepository;
import com.gdudek.movieRental.service.address.impl.AddressServiceImpl;
import com.gdudek.movieRental.service.customer.CustomerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

   private final CustomerRepository customerRepository;
   private final AddressRepository addressRepository;
   private final AddressServiceImpl addressService;

   PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository, AddressRepository addressRepository, AddressServiceImpl addressService, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.addressService = addressService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) throws NotFoundException {
        return customerRepository.findById(id).orElseThrow(()->new NotFoundException("Customer with id "+id+" not found"));
    }

    @Override
    @Transactional
    public Customer save(Object customerToSave) {
        Customer customer = (Customer) customerToSave;
        encodePassword(customer);
        Address customerAddress = customer.getAddress();

        if(addressRepository.existsByMainAddressAndCity_NameAndCity_Country_Name(customerAddress.getMainAddress()
                ,customerAddress.getCity().getName()
                ,customerAddress.getCity().getCountry().getName())){
            customerAddress= addressRepository.findAddressByMainAddressAndCity_NameAndCity_Country_Name(customerAddress.getMainAddress()
                    ,customerAddress.getCity().getName()
                    ,customerAddress.getCity().getCountry().getName()).get();
            customerAddress.getCustomers().add(customer);

            addressRepository.save(customerAddress);
            customer.setAddress(customerAddress);
            return customerRepository.save(customer);
        }

        customerAddress.getCustomers().add(customer);
        addressService.save(customerAddress);
        return customerRepository.save(customer);
    }

    private void encodePassword(Customer customer)
    {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws NotFoundException {
        if(customerRepository.existsById(id)){
            Customer customerToDelete = customerRepository.getById(id);

            customerToDelete.getAddress().getCustomers().remove(customerToDelete);
            if(customerToDelete.getAddress().getCustomers().isEmpty())
            {
                addressService.deleteById(customerToDelete.getAddress().getId());
            }
            customerRepository.deleteById(id);
        }
        else{
            throw new NotFoundException("Customer with id "+id+" not found");
        }
    }

    @Override
    public Customer findCustomerByUsername(String username) throws NotFoundException {
        return customerRepository.findCustomerByUsername(username).orElseThrow(()->new NotFoundException("Customer with username "+username+" not found"));
    }

    @Override
    public boolean existByUsername(String username) {
        return customerRepository.existsByUsername(username);
    }

    @Override
    public boolean existByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }


}
