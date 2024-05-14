package com.cinema.service;

import com.cinema.dto.reservation.BookingReadDto;
import com.cinema.dto.reservation.BookingSaveDto;
import com.cinema.dto.reservation.ReservationUserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReservationService {
    BookingReadDto getBookingData(Integer id);

    ResponseEntity<String> makeReservation(BookingSaveDto bookingSaveDto);

    List<ReservationUserDto> getAllUserReservation();

    ResponseEntity<String> payForReservation(Integer id);

    ReservationUserDto getUserReservationById(Integer id);
}
