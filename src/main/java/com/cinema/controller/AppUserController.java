package com.cinema.controller;

import com.cinema.dto.auth.ChangePasswordDto;
import com.cinema.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class AppUserController {

    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changeUserPassword(@RequestBody ChangePasswordDto changePasswordDto){
           return appUserService.changeUserPassword(changePasswordDto);
    }
}
