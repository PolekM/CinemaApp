package com.cinema.dto.seat;

import com.cinema.entity.Seat;
import lombok.Data;

@Data
public class SeatBookingReadDto {

    private Integer seatId;
    private Integer rowNumber;
    private Integer seatNumber;

    public SeatBookingReadDto(Seat seat) {
        this.seatId = seat.getSeatId();
        this.seatNumber = seat.getSeatNumber();
        this.rowNumber = seat.getRowNumber();
    }
}

