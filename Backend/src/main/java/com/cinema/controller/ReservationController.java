package com.cinema.controller;

import com.cinema.dto.reservation.BookingReadDto;
import com.cinema.dto.reservation.BookingSaveDto;
import com.cinema.dto.reservation.ReservationUserDto;
import com.cinema.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {


    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}")
    public BookingReadDto getBookingData(@PathVariable Integer id) {
        return reservationService.getBookingData(id);
    }

    @PostMapping("/add")
    public ReservationUserDto makeReservation(@RequestBody BookingSaveDto bookingSaveDto) {
        return reservationService.makeReservation(bookingSaveDto);

    }

    @GetMapping("/user")
    public List<ReservationUserDto> getAllUserReservation() {
        return reservationService.getAllUserReservation();
    }

    @GetMapping("/user/{id}")
    public ReservationUserDto getUserReservationById(@PathVariable Integer id) {
        return reservationService.getUserReservationById(id);

    }


    @PutMapping("/{id}")
    public ResponseEntity<String> payForReservation(@PathVariable Integer id) {
        return reservationService.payForReservation(id);
    }
}
