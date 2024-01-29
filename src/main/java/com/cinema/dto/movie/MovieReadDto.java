package com.cinema.dto.movie;

import com.cinema.entity.Movie;
import lombok.Data;

@Data
public class MovieReadDto {

    private Integer movieId;
    private String title;
    private String description;
    private Integer yearOfProduction;
    private String speciesName;
    private String movieUrl;

    public MovieReadDto(Movie movie) {
        this.movieId = movie.getMovieId();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.yearOfProduction = movie.getYearOfProduction();
        this.speciesName = movie.getSpecies().getSpeciesName();
        this.movieUrl = movie.getMovieUrl();
    }
}
