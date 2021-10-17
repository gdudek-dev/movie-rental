package com.gdudek.movieRental.controller;

import com.gdudek.movieRental.model.inventory.Film;
import com.gdudek.movieRental.service.inventory.impl.FilmServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(MoviesViewController.class)
public class MoviesViewControllerTest {


    @MockBean
    FilmServiceImpl filmService;


    @Autowired
    MockMvc mockMvc;

    List<Film>movies;

    @BeforeEach
    public void setUp() {
        movies = new ArrayList<>();
    }

    @Test
    public void shouldShowMoviesPage() throws Exception {
        mockMvc.perform(get("/browse/movies"))
                .andExpect(status().isOk())
                .andExpect(view().name("views/browse/movies"));
    }

    @Test
    public void shouldShowMovies() throws Exception {
        when(filmService.findAll()).thenReturn(movies);

       mockMvc.perform(get("/browse/movies"))
               .andExpect(model().attributeExists("movies"))
                 .andExpect(model().attribute("movies",Matchers.is(movies)));
    }
}
