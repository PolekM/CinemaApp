package com.cinema.service.imp;

import com.cinema.dto.movie.MovieReadDto;
import com.cinema.dto.movie.MovieUpdateDto;
import com.cinema.dto.movie.MovieSaveDto;
import com.cinema.entity.Movie;
import com.cinema.entity.Species;
import com.cinema.exception.movie.MovieNotFoundException;
import com.cinema.exception.species.SpeciesNotFoundException;
import com.cinema.repository.MovieRepository;
import com.cinema.repository.SpeciesRepository;
import com.cinema.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MovieServiceImp implements MovieService {

    private final MovieRepository movieRepository;
    private final SpeciesRepository speciesRepository;

    @Autowired
    public MovieServiceImp(MovieRepository movieRepository, SpeciesRepository speciesRepository) {
        this.movieRepository = movieRepository;
        this.speciesRepository = speciesRepository;
    }

    @Override
    public List<MovieReadDto> getAllMovie() {
        return movieRepository.findAll().stream().map(MovieReadDto::new).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> addNewMovie(MovieSaveDto movieSaveDto) {
        Species species = speciesRepository.
                findById(movieSaveDto.getSpeciesId())
                .orElseThrow(() -> new SpeciesNotFoundException("Species doesn't exist"));

        Movie movie = new Movie(movieSaveDto, species);
        movieRepository.save(movie);
        log.info("Object added correctly");
        return new ResponseEntity<>("Object added correctly", HttpStatus.OK);

    }

    @Transactional
    @Override
    public ResponseEntity<String> updateMovie(MovieUpdateDto movieUpdateDto, Integer id) {

        Movie movie = movieRepository.findById(id)
                        .orElseThrow(() -> new MovieNotFoundException("Movie doesn't exist"));

        Species species = speciesRepository.
                findById(movieUpdateDto.getSpeciesId())
                .orElseThrow(() -> new SpeciesNotFoundException("Species doesn't exist"));
        //TODO change that ugly sets
        movie.UpdateMovie(movieUpdateDto,species);
        movieRepository.save(movie);
        log.info("Object updated correctly");
        return new ResponseEntity<>("Object updated correctly", HttpStatus.OK);


    }
}
