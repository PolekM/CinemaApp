package com.cinema.exception.species;

public class SpeciesNotFoundException extends RuntimeException {

    public SpeciesNotFoundException(String message) {
        super(message);
    }
}
