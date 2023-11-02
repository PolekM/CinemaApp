package com.cinema.service.imp;

import com.cinema.dto.MovieDto;
import com.cinema.entity.Movie;
import com.cinema.repository.MovieRepository;
import com.cinema.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImp implements MovieService {

    private final MovieRepository movieRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MovieServiceImp(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MovieDto> getAllMovie() {
        return movieRepository.findAll().stream().map(this::movieDto).collect(Collectors.toList());
    }

    public MovieDto movieDto(Movie movie){
        return modelMapper.map(movie,MovieDto.class);
    }
}
