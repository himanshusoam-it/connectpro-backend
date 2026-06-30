package com.himanshu.connectpro.exception;

import com.himanshu.connectpro.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<Object>> handleDuplicate(DuplicateResourceException ex){

        ApiResponse<Object> response = ApiResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response,HttpStatus.CONFLICT);

    }

}
