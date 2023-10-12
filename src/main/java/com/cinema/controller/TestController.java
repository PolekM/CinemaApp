package com.cinema.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("/admin")
    public String adminTestController(){
        return "Admin Controller";
    }


    @GetMapping("/user")
    public String userTestController(){
        return "User Controller";
    }
}
