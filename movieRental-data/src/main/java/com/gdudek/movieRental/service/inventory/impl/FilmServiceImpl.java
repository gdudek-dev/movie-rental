package com.gdudek.movieRental.service.inventory.impl;


import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.business.Store;
import com.gdudek.movieRental.model.inventory.Category;
import com.gdudek.movieRental.model.inventory.Film;
import com.gdudek.movieRental.model.inventory.Inventory;
import com.gdudek.movieRental.repository.business.StoreRepository;
import com.gdudek.movieRental.repository.inventory.CategoryRepository;
import com.gdudek.movieRental.repository.inventory.FilmRepository;
import com.gdudek.movieRental.repository.inventory.InventoryRepository;
import com.gdudek.movieRental.service.inventory.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final CategoryRepository categoryRepository;
    private final InventoryRepository inventoryRepository;
    private final StoreRepository storeRepository;


    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public List<Film> findAllByCategory(String categoryName) throws NotFoundException {
        Category category = categoryRepository.findCategoryByName(categoryName)
                .orElseThrow(()->new NotFoundException("Category "+categoryName+" not found"));

        return filmRepository.findAllByCategoriesIn(Collections.singleton(category))
                .orElseThrow(()->new NotFoundException("Films with category "+categoryName+" not found"));
    }

    @Override
    public List<Film> findAllFilmsAvailableInStore(Long storeId) throws NotFoundException {
        List<Inventory>inventoriesByStoreId=inventoryRepository.findAllByStore_Id(storeId)
                .orElseThrow(()->new NotFoundException("Story with id "+storeId+" does not have inventory"));

        return filmRepository.findFilmByInventoryIn(inventoriesByStoreId);
    }

    @Override
    public List<Film> findAllByLanguage(String filmLanguage) throws NotFoundException {
        return filmRepository.findAllByLanguage(filmLanguage)
                .orElseThrow(()->new NotFoundException("Film with language "+filmLanguage+" not found"));
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Film findByTitle(String filmTitle) throws NotFoundException {
        return filmRepository.findFilmByTitle(filmTitle)
                .orElseThrow(()-> new NotFoundException("Film "+filmTitle+" not found"));
    }

    @Override
    @Transactional
    public Film addFilm(Film film, Store store, Category category) {

        Inventory inventory = new Inventory();
        inventory.setFilm(film);
        film.getInventory().add(inventory);

        inventory.setStore(store);
        store.getInventories().add(inventory);

        connectCategoryAndFilm(film,category);

        filmRepository.saveAndFlush(film);
        categoryRepository.save(category);
        inventoryRepository.save(inventory);

        return film;
    }

    @Override
    @Transactional
    public void deleteFilmByIdInSelectedStore(Long filmId,Long storeId) throws NotFoundException {

        if(!storeRepository.findById(storeId).isPresent()) {
            throw new NotFoundException("Store with id "+storeId+" not found");
        }

        if(!findAllFilmsAvailableInStore(storeId).contains(filmRepository.getById(filmId)))
        {
            throw new NotFoundException("Film with id "+filmId+" does not exist in store with id "+storeId);
        }

        Store store = storeRepository.getById(storeId);
        Inventory inventory = store.getInventories()
               .stream()
               .findFirst()
               .filter(inventory1 -> inventory1.getFilm().getId().equals(filmId))
               .get();

        Film film = filmRepository.getById(inventory.getFilm().getId());

        inventory.setFilm(null);
        inventory.getStore().getInventories().remove(inventory);
        inventory.setStore(null);
        inventory.getRental().forEach(rental -> rental.setInventory(null));

       if(film.getInventory().size()==1) {
           film.getCategories().stream().forEach(category -> {
               category.getFilms().remove(film);
               if (category.getFilms().isEmpty()) {
                   categoryRepository.delete(category);
               }
           });

           filmRepository.delete(film);
       }
        inventoryRepository.delete(inventory);
    }

    @Override
    @Transactional
    public Category addCategory(String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public void connectCategoryAndFilm(Film film, Category category) {
        film.getCategories().add(category);
        category.getFilms().add(film);
    }
}
