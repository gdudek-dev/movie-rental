package com.gdudek.movieRental.service.customer;

import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.customer.Customer;
import com.gdudek.movieRental.service.BasicService;

public interface CustomerService extends BasicService {

    Customer findCustomerByUsername(String username) throws NotFoundException;

}
