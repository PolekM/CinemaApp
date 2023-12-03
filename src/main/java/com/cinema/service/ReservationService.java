package com.cinema.service;

import com.cinema.dto.reservation.BookingReadDto;

public interface ReservationService {
    BookingReadDto getBookingData(Integer id);
}
