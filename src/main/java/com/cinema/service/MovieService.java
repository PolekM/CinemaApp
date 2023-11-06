package com.cinema.service;

import com.cinema.dto.MovieDto;

import java.util.List;

public interface MovieService {


    List<MovieDto> getAllMovie();

    MovieDto addNewMovie(MovieDto movieDto);
}
