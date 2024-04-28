package com.example.tienda.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TiendaNotFoundException extends RuntimeException {
    
    public TiendaNotFoundException(String message) {
        super(message);
    }


}
