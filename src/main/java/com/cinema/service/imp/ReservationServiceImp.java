package com.cinema.service.imp;

import com.cinema.dto.reservation.BookingReadDto;
import com.cinema.dto.seat.SeatBookingReadDto;
import com.cinema.entity.Room;
import com.cinema.entity.Seance;
import com.cinema.entity.Seat;
import com.cinema.exception.seance.SeanceNotFoundException;
import com.cinema.repository.*;
import com.cinema.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImp implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeanceRepository seanceRepository;
    private final SeatRepository seatRepository;
    private final RoomRepository roomRepository;

    private final MovieRepository movieRepository;

    @Autowired
    public ReservationServiceImp(ReservationRepository reservationRepository,
                                 SeanceRepository seanceRepository,
                                 SeatRepository seatRepository,
                                 RoomRepository roomRepository,
                                 MovieRepository movieRepository) {
        this.reservationRepository = reservationRepository;
        this.seanceRepository = seanceRepository;
        this.seatRepository = seatRepository;
        this.roomRepository = roomRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public BookingReadDto getBookingData(Integer id) {
        Seance seance = seanceRepository.findById(id).orElseThrow(() -> new SeanceNotFoundException("Seance doesn't exist"));
        List<SeatBookingReadDto> allSeatsInRoom= seatRepository.findAllSeatsBySeanceId(seance.getSeanceId())
                .stream().map(SeatBookingReadDto::new).collect(Collectors.toList());
        List<SeatBookingReadDto> allReservedSeatsInRoom = seatRepository.findAllReservedSeatsBySeanceId(seance.getSeanceId())
                .stream().map(SeatBookingReadDto::new).collect(Collectors.toList());
        BookingReadDto bookingReadDto = new BookingReadDto(seance,allSeatsInRoom,allReservedSeatsInRoom);
        return bookingReadDto;
    }
}
