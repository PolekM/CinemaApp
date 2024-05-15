import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ReservationUserDto } from '../entity/ReservationUserDto';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserReservationDetailsService {

  baseUrl: String = "http://localhost:8080/reservation/"

  constructor(private httpClient: HttpClient,private router: Router) { }


  getReservationById(id: number): Observable<ReservationUserDto>{
    console.log(id)
    return this.httpClient.get<ReservationUserDto>(`${this.baseUrl}`+"user/"+id)
  }
  payForReservation(id:number): Observable<String>{
    return this.httpClient.put(`${this.baseUrl}`+id, id,{ responseType: 'text' })
  }
}
