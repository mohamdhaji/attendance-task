package com.example.attendanceservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserCheckedException extends RuntimeException{

    public UserCheckedException(String message) {
        super(message);
    }
}
