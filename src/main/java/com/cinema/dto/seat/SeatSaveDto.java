package com.cinema.dto.seat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SeatSaveDto {

    private Integer rowNumber;
    private Integer seatNumber;
    private Integer roomId;
}
