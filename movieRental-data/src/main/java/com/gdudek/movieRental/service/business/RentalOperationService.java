package com.gdudek.movieRental.service.business;

import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.business.Payment;
import com.gdudek.movieRental.model.business.Rental;
import com.gdudek.movieRental.model.business.Staff;
import com.gdudek.movieRental.model.customer.Customer;
import com.gdudek.movieRental.model.inventory.Inventory;

import java.util.List;

public interface RentalOperationService {

    List<Payment> findAllPayments();
    List<Rental>findAllRental();
    List<Rental>findAllRentalMadeByCustomer(Long customerId) throws NotFoundException;
    Payment findPaymentById(Long id) throws NotFoundException;
    Rental findRentalById(Long id) throws NotFoundException;
    Payment addPayment( Rental rental);
    Rental addRental(Inventory inventory, Customer customer, Staff staff);
    void deleteRentalOperation(Long rentalId) throws NotFoundException;
}


