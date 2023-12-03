package com.cinema.service.imp;

import com.cinema.dto.reservation.BookingReadDto;
import com.cinema.dto.reservation.BookingSaveDto;
import com.cinema.dto.seat.SeatBookingReadDto;
import com.cinema.entity.*;
import com.cinema.exception.reservationStatus.ReservationStatusNotFoundException;
import com.cinema.exception.seance.SeanceNotFoundException;
import com.cinema.exception.seat.SeatNotFoundException;
import com.cinema.repository.*;
import com.cinema.service.ReservationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImp implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final SeanceRepository seanceRepository;
    private final SeatRepository seatRepository;
    private final AppUserRepository appUserRepository;
    private final ReservationStatusRepository reservationStatusRepository;
    private final ReservationSeatRepository reservationSeatRepository;

    @Autowired
    public ReservationServiceImp(ReservationRepository reservationRepository,
                                 SeanceRepository seanceRepository,
                                 SeatRepository seatRepository,
                                 AppUserRepository appUserRepository,
                                 ReservationStatusRepository reservationStatusRepository,
                                 ReservationSeatRepository reservationSeatRepository) {
        this.reservationRepository = reservationRepository;
        this.seanceRepository = seanceRepository;
        this.seatRepository = seatRepository;
        this.appUserRepository = appUserRepository;
        this.reservationStatusRepository = reservationStatusRepository;
        this.reservationSeatRepository = reservationSeatRepository;
    }

    @Override
    public BookingReadDto getBookingData(Integer id) {
        Seance seance = seanceRepository.findById(id).orElseThrow(() -> new SeanceNotFoundException("Seance doesn't exist"));
        List<SeatBookingReadDto> allSeatsInRoom = seatRepository.findAllSeatsBySeanceId(seance.getSeanceId())
                .stream().map(SeatBookingReadDto::new).collect(Collectors.toList());
        List<SeatBookingReadDto> allReservedSeatsInRoom = seatRepository.findAllReservedSeatsBySeanceId(seance.getSeanceId())
                .stream().map(SeatBookingReadDto::new).collect(Collectors.toList());
        BookingReadDto bookingReadDto = new BookingReadDto(seance, allSeatsInRoom, allReservedSeatsInRoom);
        return bookingReadDto;
    }

    @Override
    @Transactional
    public ResponseEntity<String> makeReservation(BookingSaveDto bookingSaveDto) {
        AppUser user = appUserRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        Seance seance = seanceRepository.findById(bookingSaveDto.getSeanceId())
                .orElseThrow(() -> new SeanceNotFoundException("Seance doesn't exist"));
        ReservationStatus reservationStatus = reservationStatusRepository.findReservationStatusByStatusName("UnPaid")
                .orElseThrow(() -> new ReservationStatusNotFoundException("Status doesn't exist"));
        Reservation savedReservation = reservationRepository.save(new Reservation(user, seance, bookingSaveDto, reservationStatus));

        for(Integer seatId : bookingSaveDto.getSeats()){
            Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new SeatNotFoundException("seat doesn't exist"));
            reservationSeatRepository.save(new ReservationSeat(seat,savedReservation));
        }



        return new ResponseEntity<>("Yor reservation has been created", HttpStatus.OK);
    }
}
