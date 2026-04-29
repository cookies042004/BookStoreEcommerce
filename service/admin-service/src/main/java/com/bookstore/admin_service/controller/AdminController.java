package com.bookstore.admin_service.controller;

import com.bookstore.admin_service.entity.Admin;
import com.bookstore.admin_service.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService service;

    @PostMapping
    public Admin create(@RequestBody Admin admin) {
        return service.create(admin);
    }

    @GetMapping
    public List<Admin> getAll() {
        return service.getAll();
    }
}
