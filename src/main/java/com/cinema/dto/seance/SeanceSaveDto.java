package com.cinema.dto.seance;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SeanceSaveDto {

    private Integer ticketCost;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer roomId;
    private Integer movieId;
}
