package com.gdudek.movieRental.repository.inventory;

import com.gdudek.movieRental.model.inventory.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
