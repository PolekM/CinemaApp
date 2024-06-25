import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { movieReadDto } from '../entity/movieReadDto';
import { MoviePageableDto } from '../entity/MoviePageableDto';

@Injectable({
  providedIn: 'root'
})
export class AdminProfileService {

  movieBaseUrl = 'http://localhost:8080/movie'

  constructor(private httpClient: HttpClient) { }

  getAllMovie(pageNo: number): Observable<MoviePageableDto>{
    return this.httpClient.get<MoviePageableDto>(`${this.movieBaseUrl}`+'?pageNo='+pageNo)
  }
}