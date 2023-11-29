package com.cinema.service;

import com.cinema.dto.auth.ChangePasswordDto;
import org.springframework.http.ResponseEntity;

public interface AppUserService {
    ResponseEntity<String> changeUserPassword(ChangePasswordDto changePasswordDto);
}
