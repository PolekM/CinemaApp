package com.cinema.dto;

import com.cinema.entity.Species;
import lombok.Data;

@Data
public class MovieDto {

    private String title;
    private String description;
    private Integer yearOfProduction;
    private String speciesName;

}
