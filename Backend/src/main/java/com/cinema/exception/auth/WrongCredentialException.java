package com.cinema.exception.auth;

public class WrongCredentialException extends RuntimeException {

    public WrongCredentialException(String message) {
        super(message);
    }

}
