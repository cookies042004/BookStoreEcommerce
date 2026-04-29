package com.bookstore.admin_service.service;

import com.bookstore.admin_service.entity.Admin;
import com.bookstore.admin_service.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository repo;

    public Admin create(Admin admin) {
        admin.setRole("ADMIN");
        return repo.save(admin);
    }

    public List<Admin> getAll() {
        return repo.findAll();
    }
}
