package com.gdudek.movieRental.repository.inventory;

import com.gdudek.movieRental.model.inventory.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film,Long> {
}
