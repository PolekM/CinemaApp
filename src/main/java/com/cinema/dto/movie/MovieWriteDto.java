package com.cinema.dto.movie;

import com.cinema.entity.Species;
import lombok.Data;

@Data
public class MovieWriteDto {

    private String title;
    private String description;
    private Integer yearOfProduction;
    private Species species;

}
