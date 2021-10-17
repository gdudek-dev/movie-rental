package com.gdudek.movieRental.utils;

import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.inventory.Film;
import com.gdudek.movieRental.service.inventory.impl.FilmServiceImpl;

import java.util.Collections;
import java.util.List;

public class GetMoviesByFilter {


    public List<Film> getMovies(FilmServiceImpl filmService, String filter,String filterValue)
    {
        filterValue = ConvertStringToCaseSensitive.getConvertedString(filterValue);

        try {
            switch (filter) {
                case "Category":
                   return filmService.findAllByCategory(filterValue);

                case "Title":
                    return List.of(filmService.findByTitle(filterValue));

                case "Language":
                    return filmService.findAllByLanguage(filterValue);
            }
        }catch (NotFoundException e){
            return Collections.emptyList();
        }
        return  Collections.emptyList();
    }
}
