package com.gdudek.movieRental.repository.inventory;

import com.gdudek.movieRental.model.inventory.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Optional<List<Inventory>> findAllByStore_Id(Long storeId);
}
