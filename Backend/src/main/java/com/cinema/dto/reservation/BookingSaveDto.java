package com.cinema.dto.reservation;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookingSaveDto {

    private Integer seanceId;
    private List<Integer> seats;
}
