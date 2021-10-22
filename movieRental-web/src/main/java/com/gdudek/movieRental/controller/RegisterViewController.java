package com.gdudek.movieRental.controller;

import com.gdudek.movieRental.model.customer.Customer;
import com.gdudek.movieRental.service.business.impl.StoreServiceImpl;
import com.gdudek.movieRental.service.customer.impl.CustomerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterViewController {

    private final CustomerServiceImpl customerService;
    private final StoreServiceImpl storeService;

    public RegisterViewController(CustomerServiceImpl customerService, StoreServiceImpl storeService) {
        this.customerService = customerService;
        this.storeService = storeService;
    }


    @GetMapping({"/register","register.html"})
    public String register(Model model){

        model.addAttribute("stores",storeService.findAll());
        model.addAttribute("customer",new Customer());
        model.addAttribute("emailExist",null);
        model.addAttribute("usernameExist",null);
        return "views/register";
    }

    @PostMapping({"/register","register.html"})
    public String submit(Model model,@RequestParam( value = "selectedStore")Long storeId, Customer customer) {

        if(customerService.existByUsername(customer.getUsername())){
            model.addAttribute("usernameExist","Username already exist");
            customer.setUsername(null);
            model.addAttribute("stores",storeService.findAll());
            model.addAttribute("customer",customer);
            return "views/register";
        }
        if(customerService.existByEmail(customer.getEmail())){
            model.addAttribute("emailExist","Email already exist");
            customer.setEmail(null);
            model.addAttribute("stores",storeService.findAll());
            model.addAttribute("customer",customer);
            return "views/register";
        }

        customerService.save(customer);
        storeService.addCustomer(customer,storeId);

        return "views/login";
    }
}
