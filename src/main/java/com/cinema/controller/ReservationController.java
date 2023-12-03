package com.cinema.controller;

import com.cinema.dto.reservation.BookingReadDto;
import com.cinema.dto.reservation.BookingSaveDto;
import com.cinema.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {


    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}")
    public BookingReadDto getBookingData(@PathVariable Integer id){
        return reservationService.getBookingData(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> makeReservation(@RequestBody BookingSaveDto bookingSaveDto){
        return reservationService.makeReservation(bookingSaveDto);

    }
}
