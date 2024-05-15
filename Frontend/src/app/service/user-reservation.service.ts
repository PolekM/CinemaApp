import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReservationUserDto } from '../entity/ReservationUserDto';

@Injectable({
  providedIn: 'root'
})
export class UserReservationService  {

  baseUrl: String = "http://localhost:8080/reservation/"

  constructor(private httpClient: HttpClient) { }

  getAllUserReservation(): Observable<ReservationUserDto[]>{
    return this.httpClient.get<ReservationUserDto[]>(`${this.baseUrl}`+"user")
  }

}
