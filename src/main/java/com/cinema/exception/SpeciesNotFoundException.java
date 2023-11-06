package com.cinema.exception;

public class SpeciesNotFoundException extends RuntimeException{

    public SpeciesNotFoundException(String message) {
        super(message);
    }
}
