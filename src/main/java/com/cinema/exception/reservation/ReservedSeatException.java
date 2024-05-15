package com.cinema.exception.reservation;

public class ReservedSeatException extends RuntimeException{

    public ReservedSeatException(String message) {
        super(message);
    }
}
