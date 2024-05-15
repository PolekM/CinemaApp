package com.cinema.dto.seance;


import com.cinema.entity.Seance;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SeanceReadWithStarTimeListDto {

    private Integer ticketCost;
    private String room;
    private String movie;
    private String movieUlr;
    List<SeanceStartTime> seanceStartTimeList = new ArrayList<>();

    public SeanceReadWithStarTimeListDto(Seance seance){
        this.ticketCost = seance.getTicketCost();
        this.room =  seance.getRoom().getRoomName();
        this.movie = seance.getMovie().getTitle();
        this.movieUlr = seance.getMovie().getMovieUrl();
        this.seanceStartTimeList.add(new SeanceStartTime(seance.getSeanceId(),seance.getStartTime()));
    }
}
