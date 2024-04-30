package com.example.clinica.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClinicaNotFoundException extends RuntimeException {
    
    public ClinicaNotFoundException(String message) {
        super(message);
    }


}
