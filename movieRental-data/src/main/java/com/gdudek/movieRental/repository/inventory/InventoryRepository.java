package com.gdudek.movieRental.repository.inventory;

import com.gdudek.movieRental.model.inventory.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
}
