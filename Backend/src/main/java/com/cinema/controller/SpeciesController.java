package com.cinema.controller;

import com.cinema.dto.species.SpeciesReadDto;
import com.cinema.dto.species.SpeciesSaveDto;
import com.cinema.entity.Species;
import com.cinema.service.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/species")
@CrossOrigin(origins = "http://localhost:4200")
public class SpeciesController {

    private final SpeciesService speciesService;

    @Autowired
    public SpeciesController(SpeciesService speciesService) {
        this.speciesService = speciesService;
    }

    @GetMapping()
    public List<SpeciesReadDto> getAllSpecies() {
        return speciesService.getAllSpecies();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewSpecies(@RequestBody SpeciesSaveDto speciesSaveDto) {
        return speciesService.addNewSpecies(speciesSaveDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSpecies(@RequestBody SpeciesSaveDto speciesSaveDto, @PathVariable Integer id) {
        return speciesService.updateSpecies(speciesSaveDto, id);
    }
}
