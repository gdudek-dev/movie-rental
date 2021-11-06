package com.gdudek.movieRental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminManageViewController {

    @GetMapping({"/admin/manage","/admin/manage.html"})
    public String manage(){
        return "views/admin/manage";
    }
}
