package com.example.ConfectionerWebsite.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IngredientsException.class)
    public ResponseEntity<Map<String, String>> handleNotEnoughMaterialException(IngredientsException e){
        final var errorMessage = new HashMap<String, String>();
        errorMessage.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(NotEnoughResourceException.class)
    public ResponseEntity<Map<String, String>> handleNotEnoughMaterialException(NotEnoughResourceException e){
        final var errorMessage = new HashMap<String, String>();
        errorMessage.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
