package com.cinema.dto.AppUser;

import com.cinema.entity.AppUser;
import lombok.Data;

@Data
public class UserReadDataDto {

    private String username;
    private String email;

    public UserReadDataDto(AppUser appUser){
        this.username = appUser.getUsername();
        this.email = appUser.getEmail();
    }
}
