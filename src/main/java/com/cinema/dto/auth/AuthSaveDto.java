package com.cinema.dto.auth;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AuthSaveDto {


    private String login;
    private String password;
    private String repeatPassword;
    @Email(message = "Invalid email")
    private String email;

}
