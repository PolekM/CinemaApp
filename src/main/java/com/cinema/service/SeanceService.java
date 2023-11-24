package com.cinema.service;

import com.cinema.dto.seance.SeanceReadDto;
import com.cinema.dto.seance.SeanceSaveDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SeanceService {
    ResponseEntity<String> addNewSeance(SeanceSaveDto seanceSaveDto);

    List<SeanceReadDto> getAllSeance();
}
