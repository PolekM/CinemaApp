package com.cinema.service.imp;

import com.cinema.converter.DtoConverter;
import com.cinema.dto.species.SpeciesReadDto;
import com.cinema.entity.Species;
import com.cinema.repository.SpeciesRepository;
import com.cinema.service.SpeciesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpeciesServiceImp implements SpeciesService {

    private final SpeciesRepository speciesRepository;
    private final DtoConverter dtoConverter;

    public SpeciesServiceImp(SpeciesRepository speciesRepository, DtoConverter dtoConverter) {
        this.speciesRepository = speciesRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public List<SpeciesReadDto> getAllSpecies() {
        return speciesRepository.findAll().stream().map(dtoConverter::SpeciesToSpeciesReadDto).collect(Collectors.toList());
    }
    
}
