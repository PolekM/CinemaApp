import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { movieReadDto } from '../entity/movieReadDto';
import { MoviePageableDto } from '../entity/MoviePageableDto';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  private baseUrl: String = "http://localhost:8080/movie"

  constructor(private httpClient: HttpClient) { }

  public getMovie(pageNo: number): Observable<MoviePageableDto>{
      return this.httpClient.get<MoviePageableDto>(`${this.baseUrl}`+'?pageNo='+pageNo)
  }

  public getMovieById(id:number): Observable<movieReadDto>{
    console.error(`${this.baseUrl}`+'/'+ id) 
    return this.httpClient.get<movieReadDto>(`${this.baseUrl}`+'/'+ id)
  }
}
