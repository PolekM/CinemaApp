package com.cinema.repository;

import com.cinema.entity.AppUser;
import com.cinema.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    List<Reservation> findAllByAppUser(AppUser appUser);
}
