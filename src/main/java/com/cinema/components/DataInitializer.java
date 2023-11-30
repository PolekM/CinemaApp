package com.cinema.components;

import com.cinema.dto.reservationStatus.ReservationStatusSaveDto;
import com.cinema.dto.seance.SeanceSaveDto;
import com.cinema.dto.seat.SeatSaveDto;
import com.cinema.entity.*;
import com.cinema.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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

    private final SeanceRepository seanceRepository;
    private final ReservationStatusRepository reservationStatusRepository;

    @Autowired

    public DataInitializer(AppUserRepository appUserRepository,
                           AppRoleRepository appRoleRepository,
                           MovieRepository movieRepository,
                           SpeciesRepository speciesRepository,
                           RoomRepository roomRepository,
                           SeatRepository seatRepository,
                           SeanceRepository seanceRepository,
                           ReservationStatusRepository reservationStatusRepository) {

        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.movieRepository = movieRepository;
        this.speciesRepository = speciesRepository;
        this.roomRepository = roomRepository;
        this.seatRepository = seatRepository;
        this.seanceRepository = seanceRepository;
        this.reservationStatusRepository = reservationStatusRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        appRoleRepository.saveAll(initAppRoleTable());
        appUserRepository.saveAll(initAppUserTable());
        speciesRepository.saveAll(initSpeciesTable());
        movieRepository.saveAll(initMovieTable());
        roomRepository.saveAll(initRoomTable());
        seatRepository.saveAll(initSeatTable());
        seanceRepository.saveAll(initSeanceTable());
        reservationStatusRepository.saveAll(initReservationStatusRepository());


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

    public List<Seat> initSeatTable() {
        List<SeatSaveDto> seatSaveDtoList = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            for (int j = 1; j < 5; j++) {
                seatSaveDtoList.add(new SeatSaveDto(i, j, 1));
                seatSaveDtoList.add(new SeatSaveDto(i, j, 2));
                seatSaveDtoList.add(new SeatSaveDto(i, j, 3));
            }
        }

        return seatSaveDtoList.stream().map(seatSaveDto -> new Seat(seatSaveDto, roomRepository.findById(seatSaveDto.getRoomId()).get())).collect(Collectors.toList());
    }

    public List<Seance> initSeanceTable() {
        List<SeanceSaveDto> seanceSaveDto = new ArrayList<>();
        seanceSaveDto.add(new SeanceSaveDto(
                20,
                LocalDateTime.of(2023, 11, 29, 14, 0),
                LocalDateTime.of(2023, 11, 29, 14, 0),
                1,
                1));
        seanceSaveDto.add(new SeanceSaveDto(
                20,
                LocalDateTime.of(2023, 11, 29, 16, 0),
                LocalDateTime.of(2023, 11, 29, 17, 0),
                2,
                2));
        return seanceSaveDto.stream().map(seanceDto -> new Seance(seanceDto, movieRepository.findById(seanceDto.getMovieId()).get(), roomRepository.findById(seanceDto.getRoomId()).get())).collect(Collectors.toList());
    }

    public List<ReservationStatus> initReservationStatusRepository() {
        List<ReservationStatusSaveDto> reservationStatusSaveDtoList = new ArrayList<>();
        reservationStatusSaveDtoList.add(new ReservationStatusSaveDto("UnPaid"));
        reservationStatusSaveDtoList.add(new ReservationStatusSaveDto("Paid"));
        reservationStatusSaveDtoList.add(new ReservationStatusSaveDto("Canceled"));
        return reservationStatusSaveDtoList.stream().map(ReservationStatus::new).collect(Collectors.toList());
    }
}

