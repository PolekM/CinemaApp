package com.cinema.service.imp;

import com.cinema.repository.AppUserRepository;
import com.cinema.service.AppUserService;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImp implements AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserServiceImp(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

}
