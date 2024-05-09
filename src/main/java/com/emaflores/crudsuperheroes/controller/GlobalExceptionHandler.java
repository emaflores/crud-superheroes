package com.emaflores.crudsuperheroes.controller;

import com.emaflores.crudsuperheroes.exception.SuperHeroNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Hidden
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SuperHeroNotFoundException.class)
    public ResponseEntity<String> handleSuperHeroNotFoundException(SuperHeroNotFoundException ex) {
        logger.error("SuperHero Not Found Exception: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        logger.error("Illegal Argument Exception: {}", e.getMessage());
        return ResponseEntity.badRequest().body("Invalid input: " +
                (e.getMessage().contains(":") ?
                e.getMessage().split(":")[1].trim() : e.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException e) {
        logger.error("Illegal State Exception: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
