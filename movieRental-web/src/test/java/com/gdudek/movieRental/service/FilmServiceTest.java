package com.gdudek.movieRental.service;


import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.business.Rental;
import com.gdudek.movieRental.model.business.Store;
import com.gdudek.movieRental.model.inventory.Category;
import com.gdudek.movieRental.model.inventory.Film;
import com.gdudek.movieRental.model.inventory.Inventory;
import com.gdudek.movieRental.repository.business.StoreRepository;
import com.gdudek.movieRental.repository.inventory.CategoryRepository;
import com.gdudek.movieRental.repository.inventory.FilmRepository;
import com.gdudek.movieRental.repository.inventory.InventoryRepository;
import com.gdudek.movieRental.service.inventory.impl.FilmServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
class FilmServiceTest {

    @Mock
    FilmRepository filmRepository;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    InventoryRepository inventoryRepository;

    @Mock
    StoreRepository storeRepository;

    @Mock
    Film film;
    @Mock
    Inventory inventory;
    @Mock
    Category category;
    @Mock
    Store store;
    @Mock
    Rental rental;

    @InjectMocks
    FilmServiceImpl filmService;


    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnAllFilmsByCategory() throws NotFoundException {

        //given
        when(categoryRepository.findCategoryByName(any(String.class))).thenReturn(Optional.of(category));
        when(filmRepository.findAllByCategoriesIn(anyCollection())).thenReturn(Optional.of(List.of(film)));
        //when
        List<Film>films = filmService.findAllByCategory("Action");
        //then
        assertThat(films).containsExactly(film);
    }


    @Test
    void shouldReturnAllFilmsAvailableInStore() throws NotFoundException {
        //given
        when(inventoryRepository.findAllByStore_Id(any(Long.class))).thenReturn(Optional.of(List.of(inventory)));
        when(filmRepository.findFilmByInventoryIn(List.of(inventory))).thenReturn(List.of(film));
        //when
        List<Film> films = filmService.findAllFilmsAvailableInStore(1L);
        //then
        assertThat(films).containsExactly(film);
    }

    @Test
    void shouldReturnAllFilmsByLanguage() throws NotFoundException {
        //given
        when(filmRepository.findAllByLanguage(any(String.class))).thenReturn(Optional.of(List.of(film)));
        //when
        List<Film> films = filmService.findAllByLanguage("English");
        //then
        assertThat(films).containsExactly(film);
    }

    @Test
    void shouldReturnFilmByTitle() throws NotFoundException {
        //given
        when(filmRepository.findFilmByTitle(any(String.class))).thenReturn(Optional.of(film));
        //when
        Film filmByTitle = filmService.findByTitle("Avengers");
        //then
        assertThat(filmByTitle).isEqualTo(film);
    }

    @Test
    void shouldAddFilm() throws NotFoundException {
        //given
        //when
        filmService.addFilm(film,store,category);
        //then
        verify(filmRepository).saveAndFlush(film);
        verify(categoryRepository).save(category);
        verify(inventoryRepository).save(any(Inventory.class));
    }

    @Test
    void shouldAddCategory() throws NotFoundException {
        //given
        //when
        filmService.addCategory("Action");
        //then
        verify(categoryRepository).save(any(Category.class));
    }

    @Test
    void shouldDeleteFilmByIdInSelectedStore() throws NotFoundException {

        List<Film> films = mock(List.class);
        Set<Inventory> inventories = mock(Set.class);
        Stream<Inventory> inventoryStream = mock(Stream.class);

        //given
        when(storeRepository.findById(any(Long.class))).thenReturn(Optional.of(store));
        when(inventoryRepository.findAllByStore_Id(any(Long.class))).thenReturn(Optional.of(List.of(inventory)));
        when(filmService.findAllFilmsAvailableInStore(any(Long.class))).thenReturn(films);
        when(filmRepository.getById(any(Long.class))).thenReturn(film);
        when(films.contains(any(Film.class))).thenReturn(true);
        when(storeRepository.getById(any(Long.class))).thenReturn(store);
        when(store.getInventories()).thenReturn(inventories);
        when(inventories.stream()).thenReturn(inventoryStream);
        when(inventoryStream.findFirst()).thenReturn(Optional.of(inventory));
        when(inventory.getFilm()).thenReturn(film);
        when(film.getId()).thenReturn(1L);
        when(inventory.getStore()).thenReturn(store);
        when(film.getInventory()).thenReturn(inventories);
        when(inventories.size()).thenReturn(1);
        //when
        filmService.deleteFilmByIdInSelectedStore(1L,1L);
        //then
        verify(filmRepository).delete(any(Film.class));
        verify(inventoryRepository).delete(any(Inventory.class));
    }

}