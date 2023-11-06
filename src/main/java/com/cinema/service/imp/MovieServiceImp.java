package com.cinema.service.imp;

import com.cinema.converter.DtoConverter;
import com.cinema.dto.MovieDto;
import com.cinema.entity.Movie;
import com.cinema.entity.Species;
import com.cinema.repository.MovieRepository;
import com.cinema.repository.SpeciesRepository;
import com.cinema.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImp implements MovieService {

    private final MovieRepository movieRepository;
    private final SpeciesRepository speciesRepository;
    private final DtoConverter dtoConverter;

    @Autowired
    public MovieServiceImp(MovieRepository movieRepository, DtoConverter dtoConverter,SpeciesRepository speciesRepository) {
        this.movieRepository = movieRepository;
        this.dtoConverter = dtoConverter;
        this.speciesRepository = speciesRepository;
    }

    @Override
    public List<MovieDto> getAllMovie() {
        return movieRepository.findAll().stream().map(dtoConverter::movieToMovieDto).collect(Collectors.toList());
    }

    @Override
    public MovieDto addNewMovie(MovieDto movieDto) {
        Species speciesBySpeciesName = speciesRepository.findSpeciesBySpeciesName(movieDto.getSpeciesName());
        System.out.println(speciesBySpeciesName);
        if(speciesBySpeciesName==null){
            throw new IllegalArgumentException("wrong Argument");
        }
        Movie convertedMovie = dtoConverter.movieDtoToMovie(movieDto);
        convertedMovie.setSpeciesId(speciesBySpeciesName);
        movieRepository.save(convertedMovie);

        return movieDto;
    }


}
