package com.gdudek.movieRental.controller;

import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.inventory.Film;
import com.gdudek.movieRental.service.inventory.impl.FilmServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MovieViewController {


    private final FilmServiceImpl filmService;

    public MovieViewController(FilmServiceImpl filmService) {
        this.filmService = filmService;
    }


    @GetMapping({"/browse/movies/movie","/browse/movies/movie.html"})
    public ModelAndView getMovie(@RequestParam String title) throws NotFoundException {
        String viewName = "views/browse/movie";
        Map<String,Object> model = new HashMap<>();

        Film film = filmService.findByTitle(title);

        model.put("film",film);

        return new ModelAndView(viewName,model);
    }

}
