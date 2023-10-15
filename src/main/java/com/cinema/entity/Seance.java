package com.cinema.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Seance_id")
    private Integer id;
    private Integer ticketCost;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne
    private Room roomId;
    @ManyToOne
    private Movie movieId;

}
