import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { SeanceReadWithStartTimeListDto } from '../entity/SeanceReadWithStartTimeListDto';
import { seanceReadDto } from '../entity/seanceReadDto';

@Injectable({
  providedIn: 'root'
})
export class SeanceService {

  private baseUrl: String = "http://localhost:8080/seance/"


  constructor(private httpClient: HttpClient) { }


  public getSeanceByDate(date: String): Observable<SeanceReadWithStartTimeListDto[]>{
    return this.httpClient.get<SeanceReadWithStartTimeListDto[]>(`${this.baseUrl}`+'date'+'?date='+date)
  }
public getSeanceById(id: number){
  return this.httpClient.get<seanceReadDto>(`${this.baseUrl}`+id)
}
 

}
