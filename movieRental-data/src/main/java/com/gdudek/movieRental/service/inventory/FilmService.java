package com.gdudek.movieRental.service.inventory;

import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.business.Store;
import com.gdudek.movieRental.model.inventory.Category;
import com.gdudek.movieRental.model.inventory.Film;

import java.util.List;

public interface FilmService {

    List<Film> findAll();
    List<Film> findAllByCategory(String categoryName) throws NotFoundException;
    List<Film> findAllFilmsAvailableInStore(Long storeId) throws NotFoundException;
    List<Film> findAllByLanguage(String filmLanguage) throws NotFoundException;
    List<Category> findAllCategories();
    Film findByTitle(String filmTitle) throws NotFoundException;
    Film addFilm(Film film, Store store,Category category);
    void deleteFilmByIdInSelectedStore(Long filmId,Long storeId) throws NotFoundException;
    Category addCategory(String categoryName);
    void connectCategoryAndFilm(Film film,Category category);






}
