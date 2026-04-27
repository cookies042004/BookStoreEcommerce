package com.bookstore.user_service.service;

import com.bookstore.user_service.dto.UserRequest;
import com.bookstore.user_service.dto.UserResponse;
import com.bookstore.user_service.model.User;
import com.bookstore.user_service.exception.UserNotFoundException;
import com.bookstore.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserResponse createUser(UserRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // hash password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User saved = repository.save(user);

        return mapToResponse(saved);
    }

    public UserResponse getUserById(Long id) {

        User user = repository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User with id " + id + " not found")
                );

        return mapToResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public void deleteUser(Long id) {

        if (!repository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }

        repository.deleteById(id);
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}