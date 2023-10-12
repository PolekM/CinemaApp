package com.cinema.components;

import com.cinema.entity.AppRole;
import com.cinema.entity.AppUser;
import com.cinema.repository.AppRoleRepository;
import com.cinema.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;

    @Autowired
    public DataInitializer(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        AppRole appRoleAdmin = new AppRole(null,"ROLE_ADMIN");
        AppRole appRoleUser = new AppRole(null,"ROLE_USER");
        AppUser appUserAdmin = new AppUser(null,"admin","$2a$12$YjozNDf3nr5WrC841hoh2.1pDqhmI42rJnzjU97mzAPKfLYsmeRBy","marek@marek.pl",appRoleAdmin);
        AppUser appUserNormal = new AppUser(null,"user","$2a$12$YjozNDf3nr5WrC841hoh2.1pDqhmI42rJnzjU97mzAPKfLYsmeRBy","user@user.pl",appRoleUser);

        appRoleRepository.save(appRoleAdmin);
        appRoleRepository.save(appRoleUser);
        appUserRepository.save(appUserAdmin);
        appUserRepository.save(appUserNormal);

    }
}
