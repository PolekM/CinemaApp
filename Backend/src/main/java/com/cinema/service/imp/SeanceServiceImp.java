package com.cinema.service.imp;

import com.cinema.dto.seance.*;
import com.cinema.entity.Movie;
import com.cinema.entity.Room;
import com.cinema.entity.Seance;
import com.cinema.exception.movie.MovieNotFoundException;
import com.cinema.exception.room.RoomIsBusyException;
import com.cinema.exception.room.RoomNotFoundException;
import com.cinema.exception.seance.SeanceNotFoundException;
import com.cinema.repository.MovieRepository;
import com.cinema.repository.RoomRepository;
import com.cinema.repository.SeanceRepository;
import com.cinema.service.SeanceService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SeanceServiceImp implements SeanceService {

    private final SeanceRepository seanceRepository;
    private final RoomRepository roomRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public SeanceServiceImp(SeanceRepository seanceRepository, RoomRepository roomRepository, MovieRepository movieRepository) {
        this.seanceRepository = seanceRepository;
        this.roomRepository = roomRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<SeanceReadDto> getAllSeance() {
        return seanceRepository.findAll().stream().map(SeanceReadDto::new).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ResponseEntity<String> addNewSeance(SeanceSaveDto seanceSaveDto) {
        Room room = getRoomById(seanceSaveDto.getRoomId());
        Movie movie = getMovieById(seanceSaveDto.getMovieId());
        if (!checkIfRoomIsBusy(seanceSaveDto))
            throw new RoomIsBusyException("Room is Busy");
        //ToDo - create conditional instructions to disallow create seance when room will be busy
        seanceRepository.save(new Seance(seanceSaveDto, movie, room));
        log.info("Object added correctly");
        return new ResponseEntity<>("Object added correctly", HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<String> updateSeance(SeanceSaveDto seanceSaveDto, Integer id) {
        Seance seance = seanceRepository.findById(id).
                orElseThrow(() -> new SeanceNotFoundException("Seance doesn't exist"));
        Room room = getRoomById(seanceSaveDto.getRoomId());
        Movie movie = getMovieById(seanceSaveDto.getMovieId());
        if (!checkIfRoomIsBusy(seanceSaveDto))
            throw new RoomIsBusyException("Room is Busy");
        //ToDo - create conditional instructions to disallow create seance when room will be busy
        seance.updateSeance(seanceSaveDto, movie, room);

        log.info("Object updated correctly");
        return new ResponseEntity<>("Object updated correctly", HttpStatus.OK);
    }

    @Override
    public List<SeanceReadWithStarTimeListDto> getAllSeanceByDate(LocalDate localDate) {
        List<Seance> allSeanceByDate = seanceRepository.findAllByDate(localDate);
        HashMap<String, SeanceReadWithStarTimeListDto> hashMap = new HashMap<>();
        for (Seance seance : allSeanceByDate) {
            if (!hashMap.containsKey(seance.getMovie().getTitle())) {
                hashMap.put(seance.getMovie().getTitle(),
                        new SeanceReadWithStarTimeListDto(seance));
            } else {
                hashMap.get(seance.getMovie().getTitle()).getSeanceStartTimeList().add(new SeanceStartTime(seance.getSeanceId(), seance.getStartTime()));
            }
        }

        return hashMap.values().stream().toList();
    }

    @Override
    public SeanceReadDto getSeanceById(Integer id) {
        Seance seance = seanceRepository.findById(id).orElseThrow(() -> new SeanceNotFoundException("Seance dosen't exist"));
        return new SeanceReadDto(seance);
    }
    @Override
    public List<SeanceOnScreenDto> getSeanceOnScreen() {
        return seanceRepository.findAllUniqueValue(LocalDate.now())
                .stream()
                .map(SeanceOnScreenDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public SeanceReadWithStarTimeListDto getNearestSeance(Integer id) {
        List<Seance> nearestScreening = seanceRepository.findNearestScreeningMovieById(id);
        if(nearestScreening.isEmpty()){
            return new SeanceReadWithStarTimeListDto();
        }
        SeanceReadWithStarTimeListDto seanceDto = new SeanceReadWithStarTimeListDto(nearestScreening.get(0));
        nearestScreening.stream()
                .skip(1)
                .map(seance -> new SeanceStartTime(seance.getSeanceId(),seance.getStartTime()))
                .forEach(seanceStartTime -> seanceDto.getSeanceStartTimeList().add(seanceStartTime));
        
        return  seanceDto;
    }

    private Room getRoomById(Integer roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room doesn't exist"));
    }

    private Movie getMovieById(Integer movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie doesn't exist"));
    }

    public boolean checkIfRoomIsBusy(SeanceSaveDto seanceSaveDto) {
        return seanceRepository.findRoomByStartAndEndTime(
                seanceSaveDto.getStartTime(),
                seanceSaveDto.getEndTime(),
                seanceSaveDto.getRoomId()).isEmpty();
    }
}
