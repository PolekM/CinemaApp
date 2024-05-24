package com.cinema.dto.reservation;

import com.cinema.dto.seat.SeatBookingReadDto;
import com.cinema.entity.Room;
import com.cinema.entity.Seance;
import com.cinema.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class BookingReadDto {

    private Integer seanceId;
    private String movieName;
    private Integer ticketCost;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String roomName;
    private List<SeatBookingReadDto> seatsInRoom;
    private List<SeatBookingReadDto> seatsReservedInRoom;

    public BookingReadDto(Seance seance, List<SeatBookingReadDto> allSeats, List<SeatBookingReadDto> reservedSeats) {
        this.seanceId = seance.getSeanceId();
        this.ticketCost = seance.getTicketCost();
        this.startTime = seance.getStartTime();
        this.endTime = seance.getEndTime();
        this.roomName = seance.getRoom().getRoomName();
        this.seatsInRoom = allSeats;
        this.seatsReservedInRoom = reservedSeats;
        this.movieName = seance.getMovie().getTitle();

    }
}
