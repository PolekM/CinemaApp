package com.cinema.converter;

import com.cinema.dto.species.SpeciesReadDto;
import com.cinema.dto.species.SpeciesSaveDto;
import com.cinema.dto.movie.MovieReadDto;
import com.cinema.dto.movie.MovieUpdateDto;
import com.cinema.dto.movie.MovieWriteDto;
import com.cinema.entity.Movie;
import com.cinema.entity.Species;
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

    public MovieReadDto movieToMovieReadDto(Movie movie) {
        return modelMapper.map(movie, MovieReadDto.class);
    }


    public Movie movieWriteDtoToMovie(MovieWriteDto movieWriteDto) {
        return modelMapper.map(movieWriteDto, Movie.class);
    }

    public Movie movieUpdateDtoToMovie(Integer id,MovieUpdateDto movieUpdateDto, Movie movie) {
        Movie mappedMovie = modelMapper.map(movieUpdateDto, Movie.class);
        mappedMovie.setMovieId(id);
        return mappedMovie;
    }

    public SpeciesReadDto SpeciesToSpeciesReadDto(Species species){
     return modelMapper.map(species,SpeciesReadDto.class);
    }

    public Species SpeciesSaveDtoToSpecies(SpeciesReadDto speciesReadDto){
        return modelMapper.map(speciesReadDto,Species.class);
    }

}
