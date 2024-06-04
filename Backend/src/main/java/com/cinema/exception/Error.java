package com.cinema.exception;

import lombok.Data;

import java.util.Date;

@Data
public class Error {

    private Integer code;
    private String message;
    private Date errorTime;
}
