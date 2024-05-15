package com.cinema.dto.auth;

import lombok.Data;

@Data
public class ChangePasswordDto {

    private String oldPassword;
    private String newPassword;
    private String repeatNewPassword;


}
