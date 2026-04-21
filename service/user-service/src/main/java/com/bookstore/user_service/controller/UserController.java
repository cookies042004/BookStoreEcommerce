package com.bookstore.user_service.controller;

import com.bookstore.user_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bookstore.user_service.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public User create(@RequestBody User user) {
        return service.save(user);
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping("/admin/data")
    public String getAdminData() {
        return "This is ADMIN";
    }
}
