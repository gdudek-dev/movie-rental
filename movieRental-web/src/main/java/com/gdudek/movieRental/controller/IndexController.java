package com.gdudek.movieRental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class IndexController {

    @GetMapping({"","/","index.html","/index"})
    public String index()
    {
        return "views/index";
    }
}
