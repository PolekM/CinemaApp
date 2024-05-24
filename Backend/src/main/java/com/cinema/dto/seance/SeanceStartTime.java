package com.cinema.dto.seance;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class SeanceStartTime {

    private Integer id;
    private String startTime;

    public SeanceStartTime(Integer id, LocalDateTime startTime) {
        this.id = id;
        this.startTime = startTime.getHour() + ":" + startTime.getMinute();
    }
}
