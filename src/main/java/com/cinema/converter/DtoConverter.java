package com.cinema.converter;

import com.cinema.dto.MovieDto;
import com.cinema.entity.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {

    private final ModelMapper modelMapper;

    @Autowired
    public DtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MovieDto movieToMovieDto(Movie movie){
        return modelMapper.map(movie,MovieDto.class);
    }
    public Movie movieDtoToMovie(MovieDto movieDto){
        return modelMapper.map(movieDto,Movie.class);
    }


}
