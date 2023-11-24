package com.cinema.entity;

import com.cinema.dto.seat.SeatSaveDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer SeatId;
    private Integer rowNumber;
    private Integer seatNumber;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Seat(SeatSaveDto seatSaveDto, Room room) {
        this.rowNumber = seatSaveDto.getRowNumber();
        this.seatNumber = seatSaveDto.getSeatNumber();
        this.room = room;
    }
}
