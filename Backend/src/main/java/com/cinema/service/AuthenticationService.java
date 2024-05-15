package com.cinema.service;

import com.cinema.dto.auth.AuthLoginDto;
import com.cinema.dto.auth.AuthSaveDto;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<String> createNewAccount(AuthSaveDto authSaveDto);

    ResponseEntity<String> loginToAccount(AuthLoginDto authLoginDto);
}
