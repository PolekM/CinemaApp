package com.cinema.service.imp;

import com.cinema.dto.movie.MoviePageableDto;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
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
    public MoviePageableDto getAllMovie(int pageNo, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        List<MovieReadDto> collectMovie = moviePage.getContent().stream().map(MovieReadDto::new).collect(Collectors.toList());
        return new MoviePageableDto(collectMovie,pageNo,moviePage.getTotalPages(),moviePage.getTotalElements());

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
        movie.UpdateMovie(movieUpdateDto, species);
        movieRepository.save(movie);
        log.info("Object updated correctly");
        return new ResponseEntity<>("Object updated correctly", HttpStatus.OK);
    }

    @Override
    public MovieReadDto getMovieById(int movieId) {
        MovieReadDto movieReadDto = movieRepository.findById(movieId)
                .map(MovieReadDto::new)
                .orElseThrow(() -> new MovieNotFoundException("Movie doesn't exist"));
        return movieReadDto;
    }
}
