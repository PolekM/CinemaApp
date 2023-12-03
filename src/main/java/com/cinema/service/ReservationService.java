package com.cinema.service;

import com.cinema.dto.reservation.BookingReadDto;
import com.cinema.dto.reservation.BookingSaveDto;
import org.springframework.http.ResponseEntity;

public interface ReservationService {
    BookingReadDto getBookingData(Integer id);

    ResponseEntity<String> makeReservation(BookingSaveDto bookingSaveDto);
}
