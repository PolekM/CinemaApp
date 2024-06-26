package com.cinema.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationSeatId;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public ReservationSeat(Seat seat, Reservation reservation) {
        this.seat = seat;
        this.reservation = reservation;
    }
}
