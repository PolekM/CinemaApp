package com.cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(SpeciesNotFoundException.class)
    public ResponseEntity<Error> handleSpeciesNotFoundException(SpeciesNotFoundException speciesNotFoundException){

        Error error = new Error();

        error.setCode(HttpStatus.NOT_FOUND.value());
        error.setMassage(speciesNotFoundException.getMessage());
        error.setErrorTime(new Date());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);

    }
}
