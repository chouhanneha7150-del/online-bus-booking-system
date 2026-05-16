package com.busbooking.service;

import com.busbooking.entity.User;
import com.busbooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    // SAVE USER
    public User saveUser(User user) {

        return repository.save(user);
    }

    // GET ALL USERS
    public List<User> getAllUsers() {

        return repository.findAll();
    }

    // LOGIN USER
    public User loginUser(String email, String password) {

        return repository.findByEmailAndPassword(email, password);
    }
}