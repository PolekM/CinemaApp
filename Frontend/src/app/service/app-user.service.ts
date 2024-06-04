import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SeanceOnScreenDto } from '../entity/SeanceOnScreenDto';

@Injectable({
  providedIn: 'root'
})
export class AppUserService {

  baseUrl = 'http://localhost:8080/seance'

  constructor(private http: HttpClient) { }


  getMovieOnScreen(): Observable<SeanceOnScreenDto[]>{
    return this.http.get<SeanceOnScreenDto[]>(`${this.baseUrl}`+"/onScreen")
  }
}
