package com.cinema.entity;

import com.cinema.dto.movie.MovieSaveDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Integer movieId;
    private String title;
    private String description;
    private Integer yearOfProduction;
    @ManyToOne
    @JoinColumn(name = "species_id")
    private Species species;

    public Movie(MovieSaveDto movieSaveDto, Species species){
        this.title = movieSaveDto.getTitle();
        this.description = movieSaveDto.getDescription();
        this.yearOfProduction = movieSaveDto.getYearOfProduction();
        this.species = species;
    }
}
