import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BookingReadDto } from '../entity/BookingReadDto';
import { Observable } from 'rxjs';
import { BookingSaveDto } from '../entity/BookingSaveDto';
import { ReservationUserDto } from '../entity/ReservationUserDto';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  baseUrl: String = "http://localhost:8080/reservation/"

  constructor(private http: HttpClient) { }


  public getBookingData(id: number): Observable<BookingReadDto>{
    
     return this.http.get<BookingReadDto>(`${this.baseUrl}`+id)
  }
  public makeReservation(BookingSaveDto: BookingSaveDto):Observable<ReservationUserDto>{
    return this.http.post<ReservationUserDto>(`${this.baseUrl}add`, BookingSaveDto);
    
  }
}
