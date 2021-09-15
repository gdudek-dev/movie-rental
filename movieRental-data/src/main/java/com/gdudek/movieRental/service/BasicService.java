package com.gdudek.movieRental.service;

import com.gdudek.movieRental.exception.AlreadyExistException;
import com.gdudek.movieRental.exception.NotFoundException;

import java.util.List;

public interface BasicService {

    List<?> findAll();
    Object findById(Long id) throws NotFoundException;
    Object save(Object objectToSave) throws AlreadyExistException;
    void deleteById(Long id) throws NotFoundException;


}
