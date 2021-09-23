package com.gdudek.movieRental.service.business;

import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.business.Staff;
import com.gdudek.movieRental.model.business.Store;
import com.gdudek.movieRental.model.customer.Customer;
import com.gdudek.movieRental.service.BasicService;

import java.util.List;

public interface StoreService extends BasicService {

    List<Store> findAllByManager(String firstName, String lastName) throws NotFoundException;

    void addCustomer(Customer customer,Long storeId) throws NotFoundException;
    void addStaff(Staff staff,Long storeId) throws NotFoundException;
    void addManager(Staff manager,Long storeId) throws NotFoundException;
    void deleteStaff(Long id,Long storeId) throws NotFoundException;
    void deleteCustomer(Long id,Long storeId) throws NotFoundException;
    void changeManager(Long newManagerId,Long oldManagerId,Long storeId) throws NotFoundException;
}
