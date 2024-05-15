package com.cinema.exception.seance;

public class SeanceNotFoundException extends RuntimeException {

    public SeanceNotFoundException(String message) {
        super(message);
    }
}
