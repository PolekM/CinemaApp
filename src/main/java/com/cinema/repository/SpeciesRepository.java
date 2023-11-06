package com.cinema.repository;

import com.cinema.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepository extends JpaRepository<Species,Integer> {

     Species findSpeciesBySpeciesName(String speciesName);

}
