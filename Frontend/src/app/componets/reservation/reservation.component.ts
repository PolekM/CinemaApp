import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterModule } from '@angular/router';
import { SeanceService } from '../../service/seance.service';
import { ActivatedRoute } from '@angular/router';
import { seanceReadDto } from '../../entity/seanceReadDto';
import { BookingReadDto } from '../../entity/BookingReadDto';
import { ReservationService } from '../../service/reservation.service';
import { BookingSaveDto } from '../../entity/BookingSaveDto';
import { SeatBookingReadDto } from '../../entity/SeatBookingReadDto';
import { ReservationUserDto } from '../../entity/ReservationUserDto';


@Component({
  selector: 'app-reservation',
  standalone: true,
  imports: [CommonModule,RouterModule],
  templateUrl: './reservation.component.html',
  styleUrl: './reservation.component.css'
})
export class ReservationComponent implements OnInit {

  seanceId: number= {} as number;
  SeanceDatails: BookingReadDto = {} as BookingReadDto
  selectedSeats: number[] = []
  reservationUserDto: ReservationUserDto = {} as ReservationUserDto


  constructor(private route: ActivatedRoute,private router: Router, private reservationService: ReservationService){
    this.route.params.subscribe(params =>{this.seanceId = params['id']})
  }

  ngOnInit(): void {
   this.getSeanceById(this.seanceId)
  }

  public getSeanceById(id: number){
    return this.reservationService.getBookingData(id).subscribe(Response => {this.SeanceDatails = Response})
  }

  public makeReservation(){
    const bookingSaveDto: BookingSaveDto = {seanceId: this.seanceId, seats:this.selectedSeats};
    this.reservationService.makeReservation(bookingSaveDto).subscribe(response => {this.reservationUserDto = response
     this.router.navigate(['/ticket/'+this.reservationUserDto.reservationId])
    })
    

  }

  addToSelectedSeatsList(id: number){
     const isInList = this.selectedSeats.indexOf(id)
    if(isInList != -1){
      this.selectedSeats.splice(isInList,1)
    }
    else{
      this.selectedSeats.push(id)
  
  }
  }
  isSelected(id: number): boolean{
    return this.selectedSeats.includes(id)
  }
  isReserved(seat: number): boolean{
   const t =this.SeanceDatails.seatsReservedInRoom.some(reservedSeat => reservedSeat.seatId === seat)
   return t
  }
  

}
