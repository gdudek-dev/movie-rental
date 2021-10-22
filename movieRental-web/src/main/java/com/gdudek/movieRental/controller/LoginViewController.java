package com.gdudek.movieRental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginViewController {

    @GetMapping({"/login","/login.html"})
    public String login()
    {
        return "views/login";
    }
}
