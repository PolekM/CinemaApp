package com.cinema.dto.seance;

import com.cinema.entity.Movie;
import com.cinema.entity.Room;
import com.cinema.entity.Seance;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SeanceReadDto {

    private Integer seanceId;
    private Integer ticketCost;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String room;
    private String movie;

    public SeanceReadDto(Seance seance) {
        this.seanceId = seance.getSeanceId();
        this.ticketCost = seance.getTicketCost();
        this.startTime = seance.getStartTime();
        this.endTime = seance.getEndTime();
        this.room = seance.getRoom().getRoomName();
        this.movie = seance.getMovie().getTitle();
    }
}
