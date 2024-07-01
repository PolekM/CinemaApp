package com.cinema.dto.AppUser;

import com.cinema.entity.AppRole;
import com.cinema.entity.AppUser;
import lombok.Data;

@Data
public class UserListDto {

    private Integer id;
    private String userName;
    private AppRole appRole;

    public UserListDto(AppUser appUser){
        this.id = appUser.getUserId();
        this.userName = appUser.getUsername();
        this.appRole = appUser.getUserRole();
    }
}
