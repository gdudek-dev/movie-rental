package com.gdudek.movieRental.service.business.impl;

import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.business.Payment;
import com.gdudek.movieRental.model.business.Rental;
import com.gdudek.movieRental.model.business.Staff;
import com.gdudek.movieRental.model.customer.Customer;
import com.gdudek.movieRental.model.inventory.Inventory;
import com.gdudek.movieRental.repository.business.PaymentRepository;
import com.gdudek.movieRental.repository.business.RentalRepository;
import com.gdudek.movieRental.service.business.RentalOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RentalOperationServiceImpl implements RentalOperationService {

    private final PaymentRepository paymentRepository;
    private final RentalRepository rentalRepository;


    @Override
    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public List<Rental> findAllRental() {
        return rentalRepository.findAll();
    }

    @Override
    public List<Rental> findAllRentalMadeByCustomer(Long customerId) throws NotFoundException {
        return rentalRepository.findAllByCustomer_Id(customerId).orElseThrow(()->new NotFoundException("Customer with id "+customerId+" rented nothing"));
    }

    @Override
    public Payment findPaymentById(Long id) throws NotFoundException {
        return paymentRepository.findById(id).orElseThrow(()->new NotFoundException("Payment with id "+ id +" not found"));
    }

    @Override
    public Rental findRentalById(Long id) throws NotFoundException {
        return rentalRepository.findById(id).orElseThrow(()->new NotFoundException("Rental with id "+ id +" not found"));
    }

    @Override
    @Transactional
    public Payment addPayment(Rental rental) {

        Payment payment = new Payment();

        payment.setRental(rental);
        payment.setStaff(rental.getStaff());
        payment.setCustomer(rental.getCustomer());
        payment.setAmount(rental.getInventory().getFilm().getRentalCost());

        rental.getStaff().getPayments().add(payment);
        rental.getCustomer().getPayments().add(payment);
        rental.setPayment(payment);

       return paymentRepository.save(payment);
    }

    @Override
    @Transactional
    public Rental addRental(Inventory inventory, Customer customer, Staff staff) {
        Rental rental = new Rental();
        rental.setCustomer(customer);
        rental.setStaff(staff);
        rental.setInventory(inventory);

        customer.getRentals().add(rental);
        staff.getRentals().add(rental);
        inventory.getRental().add(rental);

        return rentalRepository.save(rental);
    }

    @Override
    @Transactional
    public void deleteRentalOperation(Long rentalId) throws NotFoundException {

       Rental rental = rentalRepository.findById(rentalId).orElseThrow(()->new NotFoundException("Rental with id "+rentalId + " not found"));
       Payment payment = rental.getPayment();
       payment.getCustomer().getPayments().remove(payment);
       payment.getStaff().getPayments().remove(payment);

       paymentRepository.delete(payment);

       rental.getCustomer().getRentals().remove(rental);
       rental.getStaff().getRentals().remove(rental);
       rental.getInventory().getRental().remove(rental);

       rentalRepository.delete(rental);
    }
}
