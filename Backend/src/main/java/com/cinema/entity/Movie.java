package com.cinema.entity;

import com.cinema.dto.movie.MovieSaveDto;
import com.cinema.dto.movie.MovieUpdateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    @Column(length = 2048)
    private String description;
    private Integer yearOfProduction;
    private String movieUrl;
    @ManyToOne
    @JoinColumn(name = "species_id")
    private Species species;
        @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seance> seance;

    public Movie(MovieSaveDto movieSaveDto, Species species) {
        this.title = movieSaveDto.getTitle();
        this.description = movieSaveDto.getDescription();
        this.yearOfProduction = movieSaveDto.getYearOfProduction();
        this.species = species;
        this.movieUrl = movieSaveDto.getMovieUrl();
    }

    public Movie UpdateMovie(MovieUpdateDto movieUpdateDto, Species species) {
        this.title = movieUpdateDto.getTitle();
        this.description = movieUpdateDto.getDescription();
        this.yearOfProduction = movieUpdateDto.getYearOfProduction();
        this.species = species;
        this.movieUrl = movieUpdateDto.getMovieUrl();

        return this;
    }
}
