package com.gdudek.movieRental.repository.inventory;

import com.gdudek.movieRental.model.inventory.Category;
import com.gdudek.movieRental.model.inventory.Film;
import com.gdudek.movieRental.model.inventory.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film,Long> {

    Optional<List<Film>>findAllByCategoriesIn(Collection<Category>categories);
    Optional<List<Film>>findAllByLanguage(String language);
    Optional<Film>findFilmByTitle(String title);
    List<Film>findFilmByInventoryIn(Collection<Inventory>inventories);

}
