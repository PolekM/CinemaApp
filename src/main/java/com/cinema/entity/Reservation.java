package com.cinema.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Integer id;
    @Column(name = "user_id")
    @ManyToOne
    private AppUser appUser;
    @Column(name = "seance_id")
    @ManyToOne
    private Seance seance;
    @Column(name = "status_id")
    @ManyToOne
    private SeanceStatus seanceStatus;
}
