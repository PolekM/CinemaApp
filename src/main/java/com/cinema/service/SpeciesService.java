package com.cinema.service;

import com.cinema.dto.species.SpeciesReadDto;
import com.cinema.dto.species.SpeciesSaveDto;
import com.cinema.entity.Species;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface SpeciesService {
    List<SpeciesReadDto> getAllSpecies();

    ResponseEntity<String> addNewSpecies(SpeciesSaveDto speciesSaveDto);
}
