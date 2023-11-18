package com.cinema.dto.movie;

import lombok.Data;

@Data
public class MovieReadDto {

    private Integer MovieId;
    private String title;
    private String description;
    private Integer yearOfProduction;
    private String speciesName;

}
