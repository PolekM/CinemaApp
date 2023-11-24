package com.cinema.entity;

import com.cinema.dto.seance.SeanceSaveDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seance_id")
    private Integer seanceId;
    private Integer ticketCost;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
    @ManyToOne
    @JoinColumn(name="movie_id")
    private Movie movie;


    public Seance(SeanceSaveDto seanceSaveDto,Movie movie,Room room) {
        this.ticketCost = seanceSaveDto.getTicketCost();
        this.startTime = seanceSaveDto.getStartTime();
        this.endTime = seanceSaveDto.getEndTime();
        this.room = room;
        this.movie = movie;
    }
}
