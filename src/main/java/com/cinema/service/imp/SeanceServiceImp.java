package com.cinema.service.imp;

import com.cinema.dto.seance.SeanceReadDto;
import com.cinema.dto.seance.SeanceSaveDto;
import com.cinema.entity.Movie;
import com.cinema.entity.Room;
import com.cinema.entity.Seance;
import com.cinema.exception.movie.MovieNotFoundException;
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
        seance.updateSeance(seanceSaveDto, movie, room);

        log.info("Object updated correctly");
        return new ResponseEntity<>("Object updated correctly", HttpStatus.OK);
    }

    private Room getRoomById(Integer roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room doesn't exist"));
    }

    private Movie getMovieById(Integer movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException("Movie doesn't exist"));
    }
}
