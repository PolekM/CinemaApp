package com.cinema.dto.reservation;

import com.cinema.dto.seat.SeatBookingReadDto;
import com.cinema.entity.Reservation;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReservationUserDto {


    private Integer reservationId;
    private String movieName;
    private String movieUrl;
    private Integer ticketCost;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String roomName;

    private String reservationStatus;
    private List<SeatBookingReadDto> reservedSeats;

    public ReservationUserDto(Reservation reservation, List<SeatBookingReadDto> reservedSeats) {
        this.reservationId = reservation.getReservationId();
        this.movieName = reservation.getSeance().getMovie().getTitle();
        this.ticketCost = reservation.getPrice();
        this.startTime = reservation.getSeance().getStartTime();
        this.endTime = reservation.getSeance().getEndTime();
        this.roomName = reservation.getSeance().getRoom().getRoomName();
        this.reservedSeats = reservedSeats;
        this.reservationStatus = reservation.getReservationStatus().getStatusName();
        this.movieUrl = reservation.getSeance().getMovie().getMovieUrl();

    }
}
