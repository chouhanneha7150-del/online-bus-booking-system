package com.busbooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // HOME PAGE
    @GetMapping("/")
    public String homePage() {

        return "index";
    }

    // REGISTER PAGE
    @GetMapping("/register")
    public String registerPage() {

        return "register";
    }

    // LOGIN PAGE
    @GetMapping("/login")
    public String loginPage() {

        return "login";
    }

    // BOOKING PAGE
    @GetMapping("/booking")
    public String bookingPage() {

        return "booking";
    }

    // ADD BUS PAGE
    @GetMapping("/addbus")
    public String addBusPage() {

        return "addbus";
    }

    // SEARCH PAGE
    @GetMapping("/search")
    public String searchPage() {

        return "search";
    }

}