package com.gdudek.movieRental.service.business;

import com.gdudek.movieRental.exception.NotFoundException;
import com.gdudek.movieRental.model.business.Staff;
import com.gdudek.movieRental.service.BasicService;

public interface StaffService extends BasicService {

    Staff findStaffByUsername(String username) throws NotFoundException;
    boolean existByUsername(String username);
    boolean existByEmail(String email);
}
