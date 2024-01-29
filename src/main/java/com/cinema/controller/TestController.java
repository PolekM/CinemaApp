package com.cinema.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TestController {


    @GetMapping("/admin")
    public String adminTestController(){
        System.out.println("test");
        return "Admin Controller";
    }


    @GetMapping("/user")
    public String userTestController(){
        System.out.println("user test");
        return "User Controller";
    }
}
