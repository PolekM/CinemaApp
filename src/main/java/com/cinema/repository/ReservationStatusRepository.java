package com.cinema.repository;

import com.cinema.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationStatusRepository extends JpaRepository<ReservationStatus,Integer> {
}
