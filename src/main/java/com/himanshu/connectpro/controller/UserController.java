package com.himanshu.connectpro.controller;

import com.himanshu.connectpro.dto.ApiResponse;
import com.himanshu.connectpro.dto.RegisterRequest;
import com.himanshu.connectpro.dto.RegisterResponse;
import com.himanshu.connectpro.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(
            @Valid @RequestBody RegisterRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.register(request));
    }
}