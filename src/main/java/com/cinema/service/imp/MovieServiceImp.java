package com.cinema.service.imp;

import com.cinema.converter.DtoConverter;
import com.cinema.dto.movie.MovieReadDto;
import com.cinema.dto.movie.MovieUpdateDto;
import com.cinema.dto.movie.MovieWriteDto;
import com.cinema.entity.Movie;
import com.cinema.exception.MovieNotFoundException;
import com.cinema.exception.SpeciesNotFoundException;
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
    private final DtoConverter dtoConverter;

    @Autowired
    public MovieServiceImp(MovieRepository movieRepository, DtoConverter dtoConverter, SpeciesRepository speciesRepository) {
        this.movieRepository = movieRepository;
        this.dtoConverter = dtoConverter;
        this.speciesRepository = speciesRepository;
    }

    @Override
    public List<MovieReadDto> getAllMovie() {
        return movieRepository.findAll().stream().map(dtoConverter::movieToMovieReadDto).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> addNewMovie(MovieWriteDto movieWriteDto) {
        speciesRepository.
                findById(movieWriteDto.getSpecies().getSpecieId())
                .orElseThrow(() -> new SpeciesNotFoundException("Species doesn't exist"));

        Movie convertedMovie = dtoConverter.movieWriteDtoToMovie(movieWriteDto);
        movieRepository.save(convertedMovie);
        log.info("Object added correctly");
        return new ResponseEntity<>("Object added correctly", HttpStatus.OK);

    }

    @Transactional
    @Override
    public ResponseEntity<String> updateMovie(MovieUpdateDto movieUpdateDto, Integer id) {

            Movie movie = movieRepository.findById(id).
                    orElseThrow(() -> new MovieNotFoundException("Movie doesn't exist"));

            speciesRepository.
                    findById(movieUpdateDto.getSpecies().getSpecieId())
                    .orElseThrow(() -> new SpeciesNotFoundException("Species doesn't exist"));
            Movie updatedMovie = dtoConverter.movieUpdateDtoToMovie(id, movieUpdateDto, movie);
            movieRepository.save(updatedMovie);
            log.info("Object updated correctly");
            return new ResponseEntity<>("Object updated correctly", HttpStatus.OK);



    }
}
