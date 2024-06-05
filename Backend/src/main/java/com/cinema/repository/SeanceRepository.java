package com.cinema.repository;

import com.cinema.entity.Movie;
import com.cinema.entity.Room;
import com.cinema.entity.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Integer> {


    @Query("select e from Seance e where CAST(e.startTime AS DATE) = :localDate and e.startTime > current_timestamp ")
    List<Seance> findAllByDate(LocalDate localDate);

    @Query("select m from Seance s join Movie m on m.movieId = s.movie.movieId where CAST(s.startTime AS DATE) >= :localDate and s.startTime > current_timestamp ")
    List<Movie> findAllUniqueValue(LocalDate localDate);

    @Query("select s from Seance s where s.room.roomId = :roomId and :startTime< s.endTime and :endTime > s.startTime")
    public List<Seance> findRoomByStartAndEndTime(LocalDateTime startTime, LocalDateTime endTime, Integer roomId);
}
