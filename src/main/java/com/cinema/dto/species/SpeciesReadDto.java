package com.cinema.dto.species;

import com.cinema.entity.Species;
import lombok.Data;

@Data
public class SpeciesReadDto {

    private Integer specieId;
    private String speciesName;

    public SpeciesReadDto(Species species ) {
        this.specieId = species.getSpecieId();
        this.speciesName = species.getSpeciesName();
    }
}
