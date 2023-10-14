package com.cinema.components;

import com.cinema.entity.AppRole;
import com.cinema.entity.AppUser;
import com.cinema.entity.Movie;
import com.cinema.entity.Species;
import com.cinema.repository.AppRoleRepository;
import com.cinema.repository.AppUserRepository;
import com.cinema.repository.MovieRepository;
import com.cinema.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;

    private final MovieRepository movieRepository;

    private final SpeciesRepository speciesRepository;

    @Autowired
    public DataInitializer(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, MovieRepository movieRepository, SpeciesRepository speciesRepository) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.movieRepository = movieRepository;
        this.speciesRepository = speciesRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        appRoleRepository.saveAll(initAppRoleTable());
        appUserRepository.saveAll(initAppUserTable());
        speciesRepository.saveAll(initSpeciesTable());
        movieRepository.saveAll(initMovieTable());


    }

    public List<AppRole> initAppRoleTable() {
        List<AppRole> appRoleList = new ArrayList<>();
        appRoleList.add(new AppRole(1, "ROLE_ADMIN"));
        appRoleList.add(new AppRole(2, "ROLE_USER"));

        return appRoleList;
    }

    public List<AppUser> initAppUserTable() {
        List<AppUser> appUserList = new ArrayList<>();
        appUserList.add(new AppUser(
                1,
                "admin",
                "$2a$12$YjozNDf3nr5WrC841hoh2.1pDqhmI42rJnzjU97mzAPKfLYsmeRBy",
                "marek@marek.pl",
                initAppRoleTable().get(0)));
        appUserList.add(new AppUser(
                2,
                "user",
                "$2a$12$YjozNDf3nr5WrC841hoh2.1pDqhmI42rJnzjU97mzAPKfLYsmeRBy",
                "user@user.pl",
                initAppRoleTable().get(1)));
        return appUserList;
    }

    public List<Species> initSpeciesTable() {
        List<Species> speciesList = new ArrayList<>();
        speciesList.add(new Species(1, "Horror"));
        speciesList.add(new Species(2, "Fantasy"));

        return speciesList;
    }

    public List<Movie> initMovieTable() {
        List<Movie> movieList = new ArrayList<>();
        List<Species> speciesList = initSpeciesTable();
        movieList.add(new Movie(
                1,
                "Aquaman",
                "Some Description",
                2018,
                speciesList.get(1)
        ));
        movieList.add(new Movie(
                2,
                "The BoogeyMan",
                "Some Description",
                2023,
                speciesList.get(0)
        ));

        return movieList;
    }
}

