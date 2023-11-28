package com.cinema.controller;

import com.cinema.dto.auth.AuthSaveDto;
import com.cinema.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createNewAccount(@RequestBody @Valid AuthSaveDto authSaveDto){
        return authenticationService.createNewAccount(authSaveDto);
    }
}
