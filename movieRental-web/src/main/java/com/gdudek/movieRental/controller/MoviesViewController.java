package com.gdudek.movieRental.controller;

import com.gdudek.movieRental.model.inventory.Film;
import com.gdudek.movieRental.service.inventory.impl.FilmServiceImpl;
import com.gdudek.movieRental.utils.GetMoviesByFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class MoviesViewController {


    private final FilmServiceImpl filmService;
    private final GetMoviesByFilter getMoviesByFilter;

    public MoviesViewController(FilmServiceImpl filmService) {
        this.filmService = filmService;
        this.getMoviesByFilter = new GetMoviesByFilter();
    }

    @GetMapping({"/browse/movies","/browse/movies.html"})
    public ModelAndView getMovies(@RequestParam( value = "filter",required =false)String filter,@RequestParam( value = "filterValue",required =false)String filterValue)
    {
        String viewName = "views/browse/movies";
        Map<String,Object> model = new HashMap<>();
        List<Film> allMoviesToShow=filmService.findAll();
        List<Film> filteredMoviesToShow = new ArrayList<>();

        if(filterValue!=null) {
             filteredMoviesToShow = getMoviesByFilter.getMovies(filmService, filter, filterValue);
        }

        if(filteredMoviesToShow.isEmpty()){
            model.put("movies",allMoviesToShow);
        }else{
            model.put("movies",filteredMoviesToShow);
        }

        return new ModelAndView(viewName,model);
    }

    @PostMapping({"/browse/movies","/browse/movies.html"})
    public ModelAndView searchByFilter(String filter,String filterValue)
    {
        return getMovies(filter,filterValue);
    }
}
