package com.example.day1.user;

import com.example.day1.global.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFound(UserNotFoundException exception) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
