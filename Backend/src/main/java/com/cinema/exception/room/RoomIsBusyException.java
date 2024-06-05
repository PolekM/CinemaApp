package com.cinema.exception.room;

public class RoomIsBusyException extends RuntimeException{

    public RoomIsBusyException(String message) {
        super(message);
    }
}
