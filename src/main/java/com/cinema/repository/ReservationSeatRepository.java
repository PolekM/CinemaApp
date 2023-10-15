package com.cinema.repository;

import com.cinema.entity.Reservation;
import com.cinema.entity.ReservationSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationSeatRepository  extends JpaRepository<ReservationSeat,Integer> {
}
