package com.cinema.dto.seance;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SeanceSaveDto {

    private Integer ticketCost;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer roomId;
    private Integer movieId;
}
