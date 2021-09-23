package com.gdudek.movieRental.service;


import com.gdudek.movieRental.exception.AlreadyExistException;
import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.address.Address;
import com.gdudek.movieRental.model.business.Staff;
import com.gdudek.movieRental.model.business.Store;
import com.gdudek.movieRental.model.customer.Customer;
import com.gdudek.movieRental.repository.business.StaffRepository;
import com.gdudek.movieRental.repository.business.StoreRepository;
import com.gdudek.movieRental.repository.customer.CustomerRepository;
import com.gdudek.movieRental.service.address.impl.AddressServiceImpl;
import com.gdudek.movieRental.service.business.impl.StoreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
class StoreServiceTest {


    @Mock
    AddressServiceImpl addressService;

    @Mock
    StoreRepository storeRepository;

    @Mock
    StaffRepository staffRepository;

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    StoreServiceImpl storeService;

    @Mock
    Set<Store> stores;

    @Mock
    Set<Staff>staffSet;

    @Mock
    Set<Customer>customers;

    Store store;
    Address address;
    Customer customer;
    Staff manager;
    Staff staff;

    @BeforeEach
    void setup()
    {
        MockitoAnnotations.initMocks(this);

        address = mock(Address.class);
        store = mock(Store.class);
        manager = mock(Staff.class);
        staff=  mock(Staff.class);
        customer = mock(Customer.class);

    }

    @Test
    void shouldReturnStoreByManagerFirstNameAndLastName() throws NotFoundException {

        //given
        when(storeRepository.findAllByManager_FirstNameAndManager_LastName(any(String.class),any(String.class))).thenReturn(Optional.of(List.of(store)));
        //when
        List<Store> managedStores = storeService.findAllByManager("firstName","lastName");
        //then
        assertThat(managedStores).containsOnly(store);
    }


    @Test
    void shouldAddStore() throws AlreadyExistException {

        //given
        when(storeRepository.save(any(Store.class))).thenReturn(store);
        doReturn(stores).when(address).getStores();
        doReturn(address).when(store).getAddress();
        when(address.getStores().add(any(Store.class))).thenReturn(true);
        //when
        Store addedStore = storeService.save(store);
        //then
        assertThat(addedStore).isEqualTo(store);
    }

    @Test
    void shouldNotAddStoreWhenExistAtCertainAddress(){
        //given
        when(storeRepository.existsByAddress(address)).thenReturn(true);
        when(store.getAddress()).thenReturn(address);
        //when
        //then
        assertThatThrownBy(()->storeService.save(store)).isInstanceOf(AlreadyExistException.class);
    }

    @Test
    void shouldDeleteStore() throws NotFoundException {
        // given
        when(storeRepository.existsById(any(Long.class))).thenReturn(true);
        when(storeRepository.getById(any(Long.class))).thenReturn(store);
        doReturn(address).when(store).getAddress();
        doReturn(stores).when(address).getStores();
        when(store.getAddress().getStores().remove(store)).thenReturn(true);
        // when
        storeService.deleteById(1L);
        // then
        verify(storeRepository).deleteById(1L);
    }

    @Test
    void shouldNotDeleteStoreWhenNotFound()  {
        // given
        // when
        // then
        assertThatThrownBy(()->storeService.deleteById(1L)).isInstanceOf(NotFoundException.class);
    }

    @Test
    void shouldAddStaff() throws NotFoundException {
        // given

        when(storeRepository.findById(any(Long.class))).thenReturn(Optional.of(store));
        when(storeRepository.getById(any(Long.class))).thenReturn(store);
        when(store.getStaff()).thenReturn(staffSet);
        // when
        storeService.addStaff(staff,1L);
        // then
        verify(staff).setStore(any(Store.class));
        verify(store).getStaff();
        verify(staffSet).add(any(Staff.class));
    }

    @Test
    void shouldAddCustomer() throws NotFoundException {
        // given

        when(storeRepository.findById(any(Long.class))).thenReturn(Optional.of(store));
        when(storeRepository.getById(any(Long.class))).thenReturn(store);
        when(store.getCustomers()).thenReturn(customers);
        // when
        storeService.addCustomer(customer,1L);
        // then
        verify(customer).setStore(any(Store.class));
        verify(store).getCustomers();
        verify(customers).add(any(Customer.class));
    }

    @Test
    void shouldAddManager() throws NotFoundException {
        // given

        when(storeRepository.findById(any(Long.class))).thenReturn(Optional.of(store));
        when(storeRepository.getById(any(Long.class))).thenReturn(store);
        when(manager.getManagedStores()).thenReturn(stores);
        when(store.getStaff()).thenReturn(staffSet);
        // when
        storeService.addManager(manager,1L);
        // then

        verify(manager).setStore(any(Store.class));
        verify(manager).getManagedStores();
        verify(stores).add(store);
        verify(store).getStaff();
        verify(staffSet).add(manager);
        verify(store).setManager(any(Staff.class));
    }

    @Test
    void shouldDeleteStaff() throws NotFoundException {
        // given
        when(storeRepository.getById(any(Long.class))).thenReturn(store);
        when(store.getStaff()).thenReturn(staffSet);
        when(staffRepository.getById(any(Long.class))).thenReturn(staff);
        when(staffRepository.findById(any(Long.class))).thenReturn(Optional.of(staff));
        when(storeRepository.findById(any(Long.class))).thenReturn(Optional.of(store));
        // when
        storeService.deleteStaff(1L,2l);
        //then
        verify(staffSet).remove(staff);
        verify(staff).setStore(null);
    }

    @Test
    void shouldDeleteCustomer() throws NotFoundException {
        // given
        when(storeRepository.getById(any(Long.class))).thenReturn(store);
        when(store.getCustomers()).thenReturn(customers);
        when(customerRepository.getById(any(Long.class))).thenReturn(customer);
        when(customerRepository.findById(any(Long.class))).thenReturn(Optional.of(customer));
        when(storeRepository.findById(any(Long.class))).thenReturn(Optional.of(store));
        // when
        storeService.deleteCustomer(1L,2l);
        //then
        verify(customers).remove(customer);
        verify(customer).setStore(null);
    }

    @Test
    void shouldChangeManager() throws NotFoundException {
        // given
        when(staffRepository.findById(1L)).thenReturn(Optional.of(staff));
        when(staffRepository.findById(2L)).thenReturn(Optional.of(manager));
        when(storeRepository.findById(any(Long.class))).thenReturn(Optional.of(store));
        when(storeRepository.getById(any(Long.class))).thenReturn(store);
        when(staffRepository.getById(1L)).thenReturn(staff);
        when(staffRepository.getById(2L)).thenReturn(manager);
        when(staff.getManagedStores()).thenReturn(stores);
        when(manager.getManagedStores()).thenReturn(stores);
        // when
        storeService.changeManager(1L,2L,3L);
        // then
        verify(stores).remove(store);
        verify(stores).add(store);
        verify(store).setManager(staff);

    }
}