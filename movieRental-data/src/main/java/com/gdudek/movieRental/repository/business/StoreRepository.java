package com.gdudek.movieRental.repository.business;


import com.gdudek.movieRental.model.business.Store;
import com.gdudek.movieRental.utils.SalesByStores;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {

    @Query(value = "SELECT store_id as storeId, pay.count as salesCount FROM staff LEFT JOIN " +
            "( SELECT COUNT(payments.staff_id) AS count, payments.staff_id FROM payments GROUP BY payments.staff_id) AS pay  " +
            "ON staff.id= pay.staff_id  " +
            "GROUP BY store_id " +
            "ORDER BY  pay.count DESC",nativeQuery = true)
    List<SalesByStores> findSalesMadeByStores();


}


