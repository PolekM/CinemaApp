package com.cinema.controller;

import com.cinema.dto.seance.SeanceReadDto;
import com.cinema.dto.seance.SeanceSaveDto;
import com.cinema.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seance")
public class SeanceController {

    private final SeanceService seanceService;

    @Autowired
    public SeanceController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @GetMapping
    public List<SeanceReadDto> getAllSeance() {
        return seanceService.getAllSeance();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewSeance(@RequestBody SeanceSaveDto seanceSaveDto) {
        return seanceService.addNewSeance(seanceSaveDto);
    }
}



