package com.cinema.service.imp;

import com.cinema.repository.AppUserRepository;
import com.cinema.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImp implements AppUserService {

    private final AppUserRepository appUserRepository;

    @Autowired
    public AppUserServiceImp(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

}
