package com.cinema.service;

import com.cinema.dto.AppUser.UserListDto;
import com.cinema.dto.AppUser.UserReadDataDto;
import com.cinema.dto.auth.ChangePasswordDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppUserService {
    ResponseEntity<String> changeUserPassword(ChangePasswordDto changePasswordDto);

    UserReadDataDto getUserData();

    List<UserListDto> getAllUser(Integer pageNo, Integer pageSize);
}
