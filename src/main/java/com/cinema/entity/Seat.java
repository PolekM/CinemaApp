package com.cinema.entity;

import jakarta.persistence.*;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer rowNumber;
    private Integer seatNumber;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room roomId;

}
