package com.cinema.service;

import com.cinema.dto.movie.MovieReadDto;
import com.cinema.dto.movie.MovieUpdateDto;
import com.cinema.dto.movie.MovieSaveDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {


    List<MovieReadDto> getAllMovie(int pageNo,int pageSize);

    ResponseEntity<String> addNewMovie(MovieSaveDto movieSaveDto);

    ResponseEntity<String> updateMovie(MovieUpdateDto movieUpdateDto, Integer id);
}
