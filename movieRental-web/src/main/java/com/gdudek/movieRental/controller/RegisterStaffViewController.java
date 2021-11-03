package com.gdudek.movieRental.controller;

import com.gdudek.movieRental.model.business.Staff;
import com.gdudek.movieRental.model.customer.Customer;
import com.gdudek.movieRental.service.business.impl.StaffServiceImpl;
import com.gdudek.movieRental.service.business.impl.StoreServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterStaffViewController {

    private final StaffServiceImpl staffService;
    private final StoreServiceImpl storeService;

    public RegisterStaffViewController(StaffServiceImpl staffService, StoreServiceImpl storeService) {
        this.staffService = staffService;
        this.storeService = storeService;
    }


    @GetMapping({"/registerStaff","registerStaff.html"})
    public String register(Model model){

        model.addAttribute("stores",storeService.findAll());
        model.addAttribute("staff",new Customer());
        model.addAttribute("emailExist",null);
        model.addAttribute("usernameExist",null);
        return "views/registerStaff";
    }

    @PostMapping({"/registerStaff","registerStaff.html"})
    public String submit(Model model,@RequestParam( value = "selectedStore")Long storeId, Staff staff) {

        if(staffService.existByUsername(staff.getUsername())){
            model.addAttribute("usernameExist","Username already exist");
            staff.setUsername(null);
            model.addAttribute("stores",storeService.findAll());
            model.addAttribute("customer",staff);
            return "views/registerStaff";
        }
        if(staffService.existByEmail(staff.getEmail())){
            model.addAttribute("emailExist","Email already exist");
            staff.setEmail(null);
            model.addAttribute("stores",storeService.findAll());
            model.addAttribute("staff",staff);
            return "views/registerStaff";
        }

        staffService.save(staff);
        storeService.addStaff(staff,storeId);

        return "views/login";
    }
}
