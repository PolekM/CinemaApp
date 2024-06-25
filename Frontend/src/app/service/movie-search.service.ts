import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { movieReadDto } from '../entity/movieReadDto';

@Injectable({
  providedIn: 'root'
})
export class MovieSearchService {

  private baseUrl: String = "http://localhost:8080/movie/search"

  constructor(private httpClient: HttpClient) { }

  public searchMovie(query: string): Observable<movieReadDto[]>{
    const params = new HttpParams().set('text', query);
    return this.httpClient.get<movieReadDto[]>(`${this.baseUrl}`, {params});
  }
}
