package com.cinema.dto.seat;

import com.cinema.entity.Seat;
import lombok.Data;

@Data
public class SeatBookingReadDto {

    private Integer seatId;
    private Integer row_number;
    private Integer seat_number;

    public SeatBookingReadDto(Seat seat){
        this.seatId = seat.getSeatId();
        this.seat_number = seat.getSeatNumber();
        this.row_number = seat.getRowNumber();
    }
}

