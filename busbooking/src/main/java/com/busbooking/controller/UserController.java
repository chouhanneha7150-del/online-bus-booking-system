package com.busbooking.controller;

import com.busbooking.entity.User;
import com.busbooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    // SAVE USER
    @PostMapping("/saveUser")
    @ResponseBody
    public String saveUser(User user) {

        service.saveUser(user);

        return "User Registered Successfully";
    }

    // LOGIN USER
    @PostMapping("/loginUser")
    @ResponseBody
    public String loginUser(
            @RequestParam String email,
            @RequestParam String password
    ) {

        User user = service.loginUser(email, password);

        if (user != null) {

            return "Login Successful";
        }
        else {

            return "Invalid Email or Password";
        }
    }

    // GET ALL USERS
    @GetMapping("/users")
    @ResponseBody
    public Object getAllUsers() {

        return service.getAllUsers();
    }
}