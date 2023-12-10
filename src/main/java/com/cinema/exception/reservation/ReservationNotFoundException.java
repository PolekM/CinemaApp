package com.cinema.exception.reservation;

public class ReservationNotFoundException extends RuntimeException{

    public ReservationNotFoundException(String message) {
        super(message);
    }

}
