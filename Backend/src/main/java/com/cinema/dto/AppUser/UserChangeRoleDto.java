package com.cinema.dto.AppUser;

import com.cinema.entity.AppRole;
import lombok.Data;

@Data
public class UserChangeRoleDto {

    Integer id;
    AppRole appRole;
}
