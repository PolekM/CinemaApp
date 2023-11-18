package com.cinema.converter;

import com.cinema.dto.movie.MovieReadDto;
import com.cinema.dto.movie.MovieUpdateDto;
import com.cinema.dto.movie.MovieWriteDto;
import com.cinema.entity.Movie;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.modelmapper.convention.MatchingStrategies.LOOSE;

@Component
public class DtoConverter {

    private final ModelMapper modelMapper;

    @Autowired
    public DtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MovieReadDto movieToMovieReadDto(Movie movie) {
        return modelMapper.map(movie, MovieReadDto.class);
    }


    public Movie movieReadDtoToMovie(MovieReadDto movieReadDto) {
        return modelMapper.map(movieReadDto, Movie.class);
    }

    public MovieWriteDto movieToMovieWriteDto(Movie movie) {
        return modelMapper.map(movie, MovieWriteDto.class);
    }

    public Movie movieWriteDtoToMovie(MovieWriteDto movieWriteDto) {
        return modelMapper.map(movieWriteDto, Movie.class);
    }

    public MovieUpdateDto movieToMovieUpdateDto(Movie movie) {
        return modelMapper.map(movie, MovieUpdateDto.class);
    }

    public Movie movieUpdateDtoToMovie(Integer id,MovieUpdateDto movieUpdateDto, Movie movie) {
        Movie mappedMovie = modelMapper.map(movieUpdateDto, Movie.class);
        mappedMovie.setMovieId(id);
        return mappedMovie;
    }

}
