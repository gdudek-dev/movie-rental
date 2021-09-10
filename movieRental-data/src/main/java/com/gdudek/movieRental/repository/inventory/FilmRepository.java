package com.gdudek.movieRental.repository.inventory;

import com.gdudek.movieRental.model.inventory.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film,Long> {

    List<Film> findByReplacementCostGreaterThan(BigDecimal replacementCost);

    Optional<Film> findFirstByOrderByIdDesc();
}
