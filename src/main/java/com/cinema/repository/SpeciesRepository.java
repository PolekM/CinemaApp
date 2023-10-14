package com.cinema.repository;

import com.cinema.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeciesRepository extends JpaRepository<Species,Integer> {
}
