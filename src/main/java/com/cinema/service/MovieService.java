package com.cinema.service;

import com.cinema.dto.movie.MovieReadDto;
import com.cinema.dto.movie.MovieUpdateDto;
import com.cinema.dto.movie.MovieWriteDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {


    List<MovieReadDto> getAllMovie();

    ResponseEntity<String> addNewMovie(MovieWriteDto movieWriteDto);

    ResponseEntity<String> updateMovie(MovieUpdateDto movieUpdateDto, Integer id);
}
