package com.cinema.service;

import com.cinema.dto.species.SpeciesReadDto;
import com.cinema.entity.Species;

import java.util.List;


public interface SpeciesService {
    List<SpeciesReadDto> getAllSpecies();
}
