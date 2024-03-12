package com.cinema.service;

import com.cinema.dto.seance.SeanceReadDto;
import com.cinema.dto.seance.SeanceReadWithStarTimeListDto;
import com.cinema.dto.seance.SeanceSaveDto;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SeanceService {
    ResponseEntity<String> addNewSeance(SeanceSaveDto seanceSaveDto);

    List<SeanceReadDto> getAllSeance();

    ResponseEntity<String> updateSeance(SeanceSaveDto seanceSaveDto, Integer id);

    List<SeanceReadWithStarTimeListDto> getAllSeanceByDate(LocalDate localDate);

    SeanceReadDto getSeanceById(Integer id);
}
