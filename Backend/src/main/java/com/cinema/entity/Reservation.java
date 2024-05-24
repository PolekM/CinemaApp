package com.cinema.entity;

import com.cinema.dto.reservation.BookingSaveDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Integer reservationId;
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;
    @ManyToOne
    @JoinColumn(name = "seance_id")
    private Seance seance;
    @ManyToOne
    @JoinColumn(name = "reservation_status_id")
    private ReservationStatus reservationStatus;

    public Reservation(AppUser user, Seance seance, BookingSaveDto bookingSaveDto, ReservationStatus reservationStatus) {
        this.price = bookingSaveDto.getSeats().size() * seance.getTicketCost();
        this.appUser = user;
        this.seance = seance;
        this.reservationStatus = reservationStatus;
    }

    public Reservation changeReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
        return this;
    }

}
