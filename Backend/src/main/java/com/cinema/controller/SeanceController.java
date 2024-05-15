package com.cinema.controller;

import com.cinema.dto.seance.SeanceReadDto;
import com.cinema.dto.seance.SeanceReadWithStarTimeListDto;
import com.cinema.dto.seance.SeanceSaveDto;
import com.cinema.service.SeanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/seance")
@CrossOrigin(origins = "http://localhost:4200")
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

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSeance(@RequestBody SeanceSaveDto seanceSaveDto, @PathVariable Integer id){
        return seanceService.updateSeance(seanceSaveDto,id);
    }

    @GetMapping("/date")
    public List<SeanceReadWithStarTimeListDto> getAllSeanceByDate(
            @RequestParam(name = "date",required = false,defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate localDate){
        return seanceService.getAllSeanceByDate(localDate);
    }

    @GetMapping("/{id}")
    public SeanceReadDto getSeanceById(@PathVariable(name = "id") Integer id){
        return seanceService.getSeanceById(id);
    }

}



