package com.cinema.service.imp;

import com.cinema.dto.reservation.BookingReadDto;
import com.cinema.dto.reservation.BookingSaveDto;
import com.cinema.dto.reservation.ReservationUserDto;
import com.cinema.dto.seat.SeatBookingReadDto;
import com.cinema.entity.*;
import com.cinema.exception.auth.WrongCredentialException;
import com.cinema.exception.reservation.ReservationNotFoundException;
import com.cinema.exception.reservation.ReservedSeatException;
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

import java.util.ArrayList;
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
        Seance seance = getSeance(id);
        List<SeatBookingReadDto> allSeatsInRoom = seatRepository.findAllSeatsBySeanceId(seance.getSeanceId())
                .stream().map(SeatBookingReadDto::new).collect(Collectors.toList());
        List<SeatBookingReadDto> allReservedSeatsInRoom = seatRepository.findAllReservedSeatsBySeanceId(seance.getSeanceId())
                .stream().map(SeatBookingReadDto::new).collect(Collectors.toList());
        return new BookingReadDto(seance, allSeatsInRoom, allReservedSeatsInRoom);
    }

    @Override
    @Transactional
    public ReservationUserDto makeReservation(BookingSaveDto bookingSaveDto) {
        AppUser user = getCurrentUser();
        Seance seance = getSeance(bookingSaveDto.getSeanceId());
        ReservationStatus reservationStatus = getReservationStatus("UnPaid");
        List<SeatBookingReadDto> ListOfSeatsId = new ArrayList<>();
        Reservation savedReservation = reservationRepository.save(new Reservation(user, seance, bookingSaveDto, reservationStatus));
        List<Seat> allReservedSeatsBySeanceId = seatRepository.findAllReservedSeatsBySeanceId(seance.getSeanceId());

        for (Integer seatId : bookingSaveDto.getSeats()) {
            Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new SeatNotFoundException("seat doesn't exist"));
            if (allReservedSeatsBySeanceId.contains(seat)) {
                throw new ReservedSeatException("Seat is already taken");
            }
            ListOfSeatsId.add(new SeatBookingReadDto(seat));
            reservationSeatRepository.save(new ReservationSeat(seat, savedReservation));
        }


        return new ReservationUserDto(savedReservation, ListOfSeatsId);
    }

    @Override
    public List<ReservationUserDto> getAllUserReservation() {
        AppUser appUser = getCurrentUser();
        List<Reservation> reservationList = reservationRepository.findAllByAppUser(appUser);
        List<ReservationUserDto> reservationUserDto = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            List<ReservationSeat> allByReservation = reservationSeatRepository.findAllByReservation(reservation);
            List<SeatBookingReadDto> seatForReservation = allByReservation.stream().map(val -> new SeatBookingReadDto(val.getSeat())).collect(Collectors.toList());
            reservationUserDto.add(new ReservationUserDto(reservation, seatForReservation));

        }
        return reservationUserDto;
    }

    @Override
    public ResponseEntity<String> payForReservation(Integer id) {
        AppUser currentUser = getCurrentUser();
        Reservation reservation = getReservation(id);
        if (!currentUser.equals(reservation.getAppUser())) {
            throw new WrongCredentialException("You can`t pay for not your reservation");
        }
        if (!reservation.getReservationStatus().getStatusName().equals("UnPaid")) {
            throw new ReservedSeatException("Reservation is already Paid");
        }
        ReservationStatus reservationStatus = getReservationStatus("Paid");
        reservation.changeReservationStatus(reservationStatus);
        reservationRepository.save(reservation);


        return new ResponseEntity<>("You Paid for your reservation", HttpStatus.OK);
    }

    @Override
    public ReservationUserDto getUserReservationById(Integer id) {
        AppUser appUser = getCurrentUser();
        Reservation reservation = getReservation(id);
        if (!appUser.equals(reservation.getAppUser())) {
            throw new WrongCredentialException("Wrong User Credential");
        }
        List<SeatBookingReadDto> seats = reservationSeatRepository.findAllByReservation(reservation).stream().map(val -> new SeatBookingReadDto(val.getSeat())).toList();

        return new ReservationUserDto(reservation, seats);
    }

    public AppUser getCurrentUser() {
        return appUserRepository.findByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public Seance getSeance(Integer id) {
        return seanceRepository.findById(id)
                .orElseThrow(() -> new SeanceNotFoundException("Seance doesn't exist"));
    }

    public Reservation getReservation(Integer id) {
        return reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException("Reservation doesn't exist"));
    }

    public ReservationStatus getReservationStatus(String reservationStatus) {
        return reservationStatusRepository.findReservationStatusByStatusName(reservationStatus)
                .orElseThrow(() -> new ReservationStatusNotFoundException("Status doesn't exist"));
    }
}
