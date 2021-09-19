package com.gdudek.movieRental.service;


import com.gdudek.movieRental.exception.AlreadyExistException;
import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.address.Address;
import com.gdudek.movieRental.model.customer.Customer;
import com.gdudek.movieRental.repository.customer.CustomerRepository;
import com.gdudek.movieRental.service.address.impl.AddressServiceImpl;
import com.gdudek.movieRental.service.customer.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
class CustomerServiceTest {


    @Mock
    AddressServiceImpl addressService;

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    Set<Customer> customers;

    Customer customer;
    Address address;

    @BeforeEach
    void setup()
    {
        MockitoAnnotations.initMocks(this);

        address = mock(Address.class);
        customer = mock(Customer.class);
        customer.setId(1L);
        customer.setUsername("myUsername");
        customer.setFirstName("Tony");
        customer.setLastName("Test");
    }

    @Test
    void shouldReturnCustomerByUsername() throws NotFoundException {

        //given
        when(customerRepository.findCustomerByUsername(any(String.class))).thenReturn(Optional.of(customer));
        //when
        Customer customerByUsername = customerService.findCustomerByUsername("myUsername");
        //then
        assertThat(customerByUsername).isEqualTo(customer);
    }


    @Test
    void shouldAddCustomer() throws AlreadyExistException {

        //given
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        doReturn(customers).when(address).getCustomers();
        doReturn(address).when(customer).getAddress();
        when(address.getCustomers().add(any(Customer.class))).thenReturn(true);
        //when
        Customer addedCustomer = customerService.save(customer);
        //then
        assertThat(addedCustomer).isEqualTo(customer);
    }

    @Test
    void shouldNotAddCustomerWhenUsernameExist(){
        //given
        when(customerRepository.existsByUsername(customer.getUsername())).thenReturn(true);
        //when
        //then
        assertThatThrownBy(()->customerService.save(customer)).isInstanceOf(AlreadyExistException.class);
    }

    @Test
    void shouldNotAddWhenEmailExist()  {
        //given
        when(customerRepository.existsByEmail(customer.getUsername())).thenReturn(true);
        //when
        //then
        assertThatThrownBy(()->customerService.save(customer)).isInstanceOf(AlreadyExistException.class);
    }

    @Test
    void shouldDeleteCustomer() throws NotFoundException {
        // given
        when(customerRepository.existsById(any(Long.class))).thenReturn(true);
        when(customerRepository.getById(any(Long.class))).thenReturn(customer);
        doReturn(address).when(customer).getAddress();
        doReturn(customers).when(address).getCustomers();
        when(customer.getAddress().getCustomers().remove(customer)).thenReturn(true);
        // when
        customerService.deleteById(1L);
        // then
        verify(customerRepository).deleteById(1L);
    }

    @Test
    void shouldNotDeleteCustomerWhenNotFound()  {
        // given
        // when
        // then
        assertThatThrownBy(()->customerService.deleteById(1L)).isInstanceOf(NotFoundException.class);
    }
}