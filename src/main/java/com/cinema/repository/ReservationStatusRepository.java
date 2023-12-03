package com.cinema.repository;

import com.cinema.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationStatusRepository extends JpaRepository<ReservationStatus,Integer> {

    Optional<ReservationStatus> findReservationStatusByStatusName(String name);
}
