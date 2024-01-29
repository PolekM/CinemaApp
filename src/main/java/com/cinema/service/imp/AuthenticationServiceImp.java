package com.cinema.service.imp;

import com.cinema.dto.auth.AuthLoginDto;
import com.cinema.dto.auth.AuthSaveDto;
import com.cinema.entity.AppUser;
import com.cinema.exception.auth.UserDuplicateException;
import com.cinema.exception.auth.WrongCredentialException;
import com.cinema.exception.auth.WrongPasswordException;
import com.cinema.repository.AppUserRepository;
import com.cinema.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImp implements AuthenticationService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    private final CustomUserDetailService customUserDetailService;

    @Autowired
    public AuthenticationServiceImp(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, CustomUserDetailService customUserDetailService, AuthenticationManager authenticationManager) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailService = customUserDetailService;
    }

    @Override
    public ResponseEntity<String> createNewAccount(AuthSaveDto authSaveDto) {
        AppUser userInDb = appUserRepository.findByLogin(authSaveDto.getLogin().toLowerCase());
        if (userInDb != null) {
            throw new UserDuplicateException("User is busy");
        }
        if (!authSaveDto.getPassword().equals(authSaveDto.getRepeatPassword())) {
            throw new WrongPasswordException("Password aren`t the same");
        }
        authSaveDto.setPassword(passwordEncoder.encode(authSaveDto.getPassword()));
        AppUser newUser = new AppUser(authSaveDto);
        appUserRepository.save(newUser);

        return new ResponseEntity<>("Yor account has been created", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> loginToAccount(AuthLoginDto authLoginDto) {
        AppUser user = appUserRepository.findByLogin(authLoginDto.getLogin());
        if (user == null) {
            throw new WrongCredentialException("login or password is incorrect");
        }
        if (!passwordEncoder.matches(authLoginDto.getPassword(), user.getPassword())) {
            throw new WrongCredentialException("login or password is incorrect");
        }

        return new ResponseEntity<>(user.getUserRole().getRoleName(), HttpStatus.OK);
    }
}

