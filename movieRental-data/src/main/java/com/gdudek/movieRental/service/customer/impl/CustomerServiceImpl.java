package com.gdudek.movieRental.service.customer.impl;

import com.gdudek.movieRental.exception.AlreadyExistException;
import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.address.Address;
import com.gdudek.movieRental.model.customer.Customer;
import com.gdudek.movieRental.repository.customer.CustomerRepository;
import com.gdudek.movieRental.service.address.impl.AddressServiceImpl;
import com.gdudek.movieRental.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

   private final CustomerRepository customerRepository;
   private final AddressServiceImpl addressService;

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
    public Customer save(Object customerToSave) throws AlreadyExistException {
        Customer customer = (Customer) customerToSave;
        Address customerAddress = customer.getAddress();

        if(customerRepository.existsByUsername(customer.getUsername())){

            throw new AlreadyExistException("Customer with username "+ customer.getUsername()+" already exist");
        }

        if(customerRepository.existsByEmail(customer.getEmail())){

            throw new AlreadyExistException("Customer with email "+ customer.getEmail()+" already exist");
        }

        customerAddress.getCustomers().add(customer);
        addressService.save(customerAddress);
        return customerRepository.save(customer);
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
}
