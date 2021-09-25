package com.gdudek.movieRental.service;


import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.business.Payment;
import com.gdudek.movieRental.model.business.Rental;
import com.gdudek.movieRental.model.business.Staff;
import com.gdudek.movieRental.model.customer.Customer;
import com.gdudek.movieRental.model.inventory.Film;
import com.gdudek.movieRental.model.inventory.Inventory;
import com.gdudek.movieRental.repository.business.PaymentRepository;
import com.gdudek.movieRental.repository.business.RentalRepository;
import com.gdudek.movieRental.service.business.impl.RentalOperationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
class RentalOperationServiceTest {


    @Mock
    RentalRepository rentalRepository;

    @Mock
    PaymentRepository paymentRepository;



    @Mock
    Staff staff;
    @Mock
    Inventory inventory;
    @Mock
    Customer customer;
    @Mock
    Payment payment;
    @Mock
    Rental rental;
    @Mock
    Film film;

    @InjectMocks
    RentalOperationServiceImpl rentalOperationService;


    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnAllRentalMadeByCustomer() throws NotFoundException {

        //given
        when(rentalRepository.findAllByCustomer_Id(any(Long.class))).thenReturn(Optional.of(List.of(rental)));
        //when
        List<Rental> rentalList = rentalOperationService.findAllRentalMadeByCustomer(1L);
        //then
        assertThat(rentalList).contains(rental);
    }


    @Test
    void shouldAddRental()  {
        //given
        when(rentalRepository.save(any(Rental.class))).thenReturn(rental);
        //when
        Rental addedRental = rentalOperationService.addRental(new Inventory(),new Customer(),new Staff());
        //then
        assertThat(addedRental).isEqualTo(rental);
    }

    @Test
    void shouldAddPayment(){
        //given
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);
        when(rental.getInventory()).thenReturn(inventory);
        when(inventory.getFilm()).thenReturn(film);
        when(rental.getStaff()).thenReturn(staff);
        when(rental.getCustomer()).thenReturn(customer);
        //when
        Payment addedPayment = rentalOperationService.addPayment(rental);
        //then
        assertThat(addedPayment).isEqualTo(payment);
    }

    @Test
    void shouldDeleteRentalOperationRecord() throws NotFoundException {
        //given
        when(rentalRepository.findById(any(Long.class))).thenReturn(Optional.of(rental));
        when(rental.getPayment()).thenReturn(payment);
        when(payment.getCustomer()).thenReturn(customer);
        when(payment.getStaff()).thenReturn(staff);
        when(rental.getCustomer()).thenReturn(customer);
        when(rental.getStaff()).thenReturn(staff);
        when(rental.getInventory()).thenReturn(inventory);
        //when
        rentalOperationService.deleteRentalOperation(1L);
        //then
        verify(paymentRepository).delete(payment);
        verify(rentalRepository).delete(rental);
    }
}