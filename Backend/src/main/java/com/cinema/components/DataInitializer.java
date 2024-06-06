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
import java.time.temporal.ChronoUnit;
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
        speciesList.add(new Species(3, "Animation"));
        speciesList.add(new Species(4, "Family"));
        speciesList.add(new Species(5, "Adventure"));

        return speciesList;
    }

    public List<Movie> initMovieTable() {
        List<Movie> movieList = new ArrayList<>();
        List<Species> speciesList = initSpeciesTable();
        movieList.add(new Movie(
                1,
                "Aquaman",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                2018,
                "https://fwcdn.pl/fpo/61/14/826114/8102089_1.3.jpg",
                speciesList.get(1)
        ));
        movieList.add(new Movie(
                2,
                "The BoogeyMan",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                2023,
                "https://upload.wikimedia.org/wikipedia/en/8/89/The_Boogeyman_2023_poster.png",
                speciesList.get(0)
        ));
        movieList.add(new Movie(
                3,
                "The Boy and the Heron",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                2023,
                "https://m.media-amazon.com/images/M/MV5BNmI2MzJkYzYtM2Y2My00NmJmLTgxZDAtODAwNjBmM2RlZjRhXkEyXkFqcGdeQXVyMTUzMTg2ODkz._V1_.jpg",
                speciesList.get(3)
        ));
        movieList.add(new Movie(
                4,
                "Spirited Away",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                2001,
                "https://m.media-amazon.com/images/M/MV5BMjlmZmI5MDctNDE2YS00YWE0LWE5ZWItZDBhYWQ0NTcxNWRhXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_FMjpg_UX1000_.jpg",
                speciesList.get(3)
        ));
        movieList.add(new Movie(
                5,
                "The Witch",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                2015,
                "https://m.media-amazon.com/images/M/MV5BMTUyNzkwMzAxOF5BMl5BanBnXkFtZTgwMzc1OTk1NjE@._V1_.jpg",
                speciesList.get(0)
        ));
        movieList.add(new Movie(
                6,
                "Avatar 2",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                2023,
                "https://fwcdn.pl/fpo/81/78/558178/8047434.3.jpg",
                speciesList.get(1)
        ));
        movieList.add(new Movie(
                7,
                "Winnie The Pooh - Blood And Honey",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                2023,
                "https://ostatniatawerna.pl/wp-content/uploads/2022/07/Winne-the-Pooh-Blood-and-Honey-poster.jpg",
                speciesList.get(0)
        ));
        movieList.add(new Movie(
                8,
                "Alien",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                1998,
                "https://m.media-amazon.com/images/I/91uvgkhj88L._AC_UF1000,1000_QL80_.jpg",
                speciesList.get(0)
        ));
        movieList.add(new Movie(
                9,
                "Momonoke Princesse",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                1997,
                "https://ecsmedia.pl/c/princess-mononoke-b-iext123243006.jpg",
                speciesList.get(3)
        ));
        movieList.add(new Movie(
                10,
                "Star Wars",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                1977,
                "https://tapetuj.pl/userdata/public/gfx/26327/Fototapeta-Star-Wars-EP7-Official-Movie-Poster-VD-046.jpg",
                speciesList.get(1)
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
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).plusHours(2),
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).plusHours(3),
                1,
                1));
        seanceSaveDto.add(new SeanceSaveDto(
                20,
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).plusHours(2),
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).plusHours(3),
                1,
                1));
        seanceSaveDto.add(new SeanceSaveDto(
                20,
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(3),
                2,
                2));
        seanceSaveDto.add(new SeanceSaveDto(
                20,
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).plusDays(1),
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).plusDays(1).plusHours(2),
                2,
                2));
        seanceSaveDto.add(new SeanceSaveDto(
                20,
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).plusDays(1).plusHours(3),
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).plusDays(1).plusHours(5),
                2,
                2));
        seanceSaveDto.add(new SeanceSaveDto(
                20,
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).plusDays(1).plusHours(3),
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).plusDays(1).plusHours(5),
                1,
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

