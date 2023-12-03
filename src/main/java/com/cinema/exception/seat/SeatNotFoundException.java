package com.cinema.exception.seat;

public class SeatNotFoundException extends RuntimeException{

    public SeatNotFoundException(String message) {
        super(message);
    }
}
