package com.cinema.repository;

import com.cinema.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {


    @Query("select s FROM Seat s " +
            "join Room r on r.roomId = s.room.roomId " +
            "join Seance se on se.room.roomId = r.roomId where se.seanceId=:seanceId")
    List<Seat> findAllSeatsBySeanceId(Integer seanceId);

    @Query("select s from Seat s " +
            "join ReservationSeat rs on rs.seat.SeatId = s.SeatId " +
            "join Reservation r on r.reservationId = rs.reservation.reservationId " +
            "join Seance se on se.seanceId = r.seance.seanceId where se.seanceId=:seanceId")
    List<Seat> findAllReservedSeatsBySeanceId(Integer seanceId);
}
