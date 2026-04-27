package com.bookstore.user_service.controller;

import com.bookstore.user_service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestParam String username) {

        String role = "USER";

        if ("admin".equals(username)) {
            role = "ADMIN";
        }

        return jwtUtil.generateToken(username, role);
    }
}
