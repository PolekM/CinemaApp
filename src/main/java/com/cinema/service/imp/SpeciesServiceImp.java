package com.cinema.service.imp;

import com.cinema.converter.DtoConverter;
import com.cinema.dto.species.SpeciesReadDto;
import com.cinema.dto.species.SpeciesSaveDto;
import com.cinema.entity.Species;
import com.cinema.exception.species.SpeciesDuplicateException;
import com.cinema.exception.species.SpeciesNotFoundException;
import com.cinema.repository.SpeciesRepository;
import com.cinema.service.SpeciesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        return speciesRepository.findAll().stream().map(dtoConverter::speciesToSpeciesReadDto).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<String> addNewSpecies(SpeciesSaveDto speciesSaveDto) {
        Optional<Species> speciesBySpeciesName = speciesRepository.findSpeciesBySpeciesName(speciesSaveDto.getSpeciesName());
        if(speciesBySpeciesName.isPresent()){
            throw new SpeciesDuplicateException("The object is already in the database");
        }
        speciesRepository.save(dtoConverter.speciesSaveDtoToSpecies(speciesSaveDto));
        return new ResponseEntity<>("Object added correctly", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateSpecies(SpeciesSaveDto speciesSaveDto, Integer id) {
        Species species = speciesRepository.findById(id).orElseThrow(() -> new SpeciesNotFoundException("Species doesn't exist"));
        species.setSpeciesName(speciesSaveDto.getSpeciesName());
        speciesRepository.save(species);
        return new ResponseEntity<>("Object updated correctly", HttpStatus.OK);
    }
}
