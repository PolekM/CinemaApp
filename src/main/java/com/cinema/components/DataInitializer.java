package com.cinema.components;

import com.cinema.converter.DtoConverter;
import com.cinema.dto.seat.SeatSaveDto;
import com.cinema.entity.*;
import com.cinema.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataInitializer implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;

    private final MovieRepository movieRepository;

    private final SpeciesRepository speciesRepository;

    private final RoomRepository roomRepository;

    private final SeatRepository seatRepository;
    private final DtoConverter dtoConverter;

    @Autowired
    public DataInitializer(AppUserRepository appUserRepository,
                           AppRoleRepository appRoleRepository,
                           MovieRepository movieRepository,
                           SpeciesRepository speciesRepository,
                           RoomRepository roomRepository,
                           SeatRepository seatRepository,
                           DtoConverter dtoConverter) {

        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.movieRepository = movieRepository;
        this.speciesRepository = speciesRepository;
        this.roomRepository = roomRepository;
        this.seatRepository = seatRepository;
        this.dtoConverter = dtoConverter;
    }


    @Override
    public void run(String... args) throws Exception {

        appRoleRepository.saveAll(initAppRoleTable());
        appUserRepository.saveAll(initAppUserTable());
        speciesRepository.saveAll(initSpeciesTable());
        movieRepository.saveAll(initMovieTable());
        roomRepository.saveAll(initRoomTable());
        seatRepository.saveAll(initSeatTable());


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

    public List<Room> initRoomTable() {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room(1, "A", 20));
        roomList.add(new Room(2, "B", 20));
        roomList.add(new Room(3, "C", 20));

        return roomList;
    }
    public List<Seat> initSeatTable(){
        List<SeatSaveDto> seatSaveDto = new ArrayList<>();
        for(int i=1;i<6;i++){
            for(int j=1;j<5;j++){
                seatSaveDto.add(new SeatSaveDto(i,j,1));
                seatSaveDto.add(new SeatSaveDto(i,j,2));
                seatSaveDto.add(new SeatSaveDto(i,j,3));
            }
        }

        return seatSaveDto.stream().map(dtoConverter::seatSaveDtoToSeat).collect(Collectors.toList());
    }
}

