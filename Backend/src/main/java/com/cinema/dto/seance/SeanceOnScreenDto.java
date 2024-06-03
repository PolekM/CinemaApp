package com.cinema.dto.seance;

import com.cinema.entity.Movie;
import lombok.Data;

@Data
public class SeanceOnScreenDto {

    private Integer id;
    private String title;
    private String movieUrl;

    public SeanceOnScreenDto(Movie movie){
        this.id = movie.getMovieId();
        this.title = movie.getTitle();
        this.movieUrl = movie.getMovieUrl();
    }


}
