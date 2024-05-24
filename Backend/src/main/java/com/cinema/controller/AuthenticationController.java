package com.cinema.controller;

import com.cinema.dto.auth.AuthLoginDto;
import com.cinema.dto.auth.AuthSaveDto;
import com.cinema.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createNewAccount(@RequestBody @Valid AuthSaveDto authSaveDto) {
        return authenticationService.createNewAccount(authSaveDto);
    }

    @PostMapping("/custom-login")
    public ResponseEntity<String> loginToAccount(@RequestBody AuthLoginDto authLoginDto) {
        System.out.println(authLoginDto.getLogin());
        return authenticationService.loginToAccount(authLoginDto);
    }

}
