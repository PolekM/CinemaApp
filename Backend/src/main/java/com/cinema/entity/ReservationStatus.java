package com.cinema.entity;

import com.cinema.dto.reservationStatus.ReservationStatusSaveDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="reservation_status_id")
    private Integer reservationStatusId;
    private String statusName;

    public ReservationStatus(ReservationStatusSaveDto reservationStatusSaveDto){
        this.statusName = reservationStatusSaveDto.getStatusName();
    }
}
