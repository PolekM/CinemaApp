package com.cinema.repository;

import com.cinema.entity.SeanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeanceStatusRepository extends JpaRepository<SeanceStatus,Integer> {
}
