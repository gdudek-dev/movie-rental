package com.gdudek.movieRental.repositories;

import com.gdudek.movieRental.model.business.Payment;
import com.gdudek.movieRental.model.business.Staff;
import com.gdudek.movieRental.model.business.Store;
import com.gdudek.movieRental.repository.business.PaymentRepository;
import com.gdudek.movieRental.repository.business.StaffRepository;
import com.gdudek.movieRental.repository.business.StoreRepository;
import com.gdudek.movieRental.utils.SalesByStores;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
class StoreRepositoryTest {

    @Autowired
    private  StoreRepository storeRepository;
    @Autowired
    private  StaffRepository staffRepository;
    @Autowired
    private  PaymentRepository paymentRepository;

    Staff staff;
    Store store;
    Payment payment;

    @BeforeEach
    void setup()
    {
        staff = new Staff();
        staffRepository.saveAndFlush(staff);
        payment = new Payment();
        payment.setAmount(BigDecimal.valueOf(999.99));
        paymentRepository.saveAndFlush(payment);
         store = new Store();
        storeRepository.saveAndFlush(store);

        staff.setStore(store);
        store.getStaff().add(staff);
        payment.setStaff(staff);
        staff.getPayments().add(payment);

        staffRepository.saveAndFlush(staff);
        storeRepository.saveAndFlush(store);
        paymentRepository.saveAndFlush(payment);
    }

    @Test
    void findSalesMadeByStores() {

        List<SalesByStores> salesMadeByStores = new ArrayList<>();
        storeRepository.findSalesMadeByStores().forEach(salesMadeByStores::add);
        Assert.notEmpty(salesMadeByStores, "SalesMadeByStores mustn't be empty");
        Assertions.assertEquals(store,storeRepository.getById(salesMadeByStores.stream()
                .filter(salesByStores -> store.getId().equals(salesByStores.getStoreId()))
                .findAny()
                .orElseThrow().getStoreId()));
    }
}