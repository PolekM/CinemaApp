package com.cinema.repository;

import com.cinema.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpeciesRepository extends JpaRepository<Species, Integer> {

    Optional<Species> findSpeciesBySpeciesName(String speciesName);

}
