package com.himanshu.connectpro.service.impl;

import java.time.LocalDateTime;

import com.himanshu.connectpro.dto.ApiResponse;
import com.himanshu.connectpro.dto.RegisterResponse;
import com.himanshu.connectpro.entity.User;
import com.himanshu.connectpro.exception.DuplicateResourceException;
import com.himanshu.connectpro.mapper.UserMapper;


import com.himanshu.connectpro.dto.RegisterRequest;
import com.himanshu.connectpro.entity.User;
import com.himanshu.connectpro.repository.UserRepository;
import com.himanshu.connectpro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse<RegisterResponse> register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        if (userRepository.existsByPhone(request.getPhone())) {
            throw new DuplicateResourceException("Phone number already exists");
        }

        User user = UserMapper.toEntity(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);

        User savedUser = userRepository.save(user);

        RegisterResponse response = UserMapper.toResponse(savedUser);

        return ApiResponse.<RegisterResponse>builder()
                .success(true)
                .message("User registered successfully")
                .data(response)
                .timestamp(LocalDateTime.now())
                .build();
    }



}