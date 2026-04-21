package com.bookstore.user_service.controller;

import com.bookstore.user_service.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestParam String username) {
        String role = "USER";

        if(username.equals("admin")){
            role = "ADMIN";
        }

        return JwtUtil.generateToken(username, role);
    }
}