package com.gdudek.movieRental.controller;

import com.gdudek.movieRental.exception.AlreadyExistException;
import com.gdudek.movieRental.model.customer.Customer;
import com.gdudek.movieRental.service.business.impl.StoreServiceImpl;
import com.gdudek.movieRental.service.customer.impl.CustomerServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
        return "views/register";
    }

    @PostMapping({"/register","register.html"})
    public ModelAndView submit(@RequestParam( value = "selectedStore")Long storeId, Customer customer, BindingResult bindingResult) throws AlreadyExistException {

        if (bindingResult.hasErrors()) {
            return new ModelAndView();
        }
        customerService.save(customer);
        storeService.addCustomer(customer,storeId);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/index");

        return new ModelAndView(redirectView);
    }
}
