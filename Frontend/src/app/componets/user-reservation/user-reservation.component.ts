import { Component, OnInit } from '@angular/core';
import { UserReservationService } from '../../service/user-reservation.service';
import { ReservationUserDto } from '../../entity/ReservationUserDto';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-user-reservation',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './user-reservation.component.html',
  styleUrl: './user-reservation.component.css'
})
export class UserReservationComponent implements OnInit{

  userTicket: ReservationUserDto[] = []

  constructor(private userReservationService: UserReservationService){}
  ngOnInit(): void {
   this.getAllUserReservation()
  }

  public getAllUserReservation(){
    this.userReservationService.getAllUserReservation().subscribe(response => {this.userTicket = response});
  }
}
