import { Component, OnInit } from '@angular/core';
import { UserReservationDetailsService } from '../../service/user-reservation-details.service';
import { ReservationUserDto } from '../../entity/ReservationUserDto';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-user-reservation-details',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './user-reservation-details.component.html',
  styleUrl: './user-reservation-details.component.css'
})
export class UserReservationDetailsComponent implements OnInit{

  tickedDetail: ReservationUserDto = {} as ReservationUserDto;
  ticketId: number = {} as number

  constructor(private userReservationDetails: UserReservationDetailsService, private route: ActivatedRoute, private router: Router){
    
  }
  ngOnInit(): void {
    this.route.params.subscribe(params =>{this.ticketId = params['id']})
    this.getReservationById(this.ticketId)
  }

  getReservationById(id: number){
    this.userReservationDetails.getReservationById(id).subscribe(response => {this.tickedDetail = response})
  }

  payForReservation(id:number){
    console.log(id)
    this.userReservationDetails.payForReservation(id).subscribe(response => {
      this.router.navigate(['/ticket'])
    }, error => {
      console.error('Error:', error);
    });
};
  
  


}
