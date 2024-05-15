package com.cinema.dto.movie;

import lombok.Data;

@Data
public class MovieSaveDto {

    private String title;
    private String description;
    private Integer yearOfProduction;
    private Integer speciesId;
    private String movieUrl;

}
