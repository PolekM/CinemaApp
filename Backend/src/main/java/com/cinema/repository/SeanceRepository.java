package com.cinema.repository;

import com.cinema.entity.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Integer> {


    @Query("select e from Seance e where CAST(e.startTime AS DATE) = :localDate and e.startTime > current_timestamp ")
    List<Seance> findAllByDate(LocalDate localDate);
}
