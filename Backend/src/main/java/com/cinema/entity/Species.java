package com.cinema.entity;

import com.cinema.dto.species.SpeciesSaveDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specie_id")
    private Integer specieId;
    @Column(name = "name")
    private String speciesName;

    public Species(SpeciesSaveDto speciesSaveDto) {
        this.speciesName = speciesSaveDto.getSpeciesName();
    }
}
