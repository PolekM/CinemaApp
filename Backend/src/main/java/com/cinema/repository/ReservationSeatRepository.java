package com.cinema.repository;

import com.cinema.entity.Reservation;
import com.cinema.entity.ReservationSeat;
import com.cinema.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationSeatRepository  extends JpaRepository<ReservationSeat,Integer> {

    List<ReservationSeat> findAllByReservation(Reservation reservation);
    List<ReservationSeat> findAllByReservationSeatId(Integer id);
}
