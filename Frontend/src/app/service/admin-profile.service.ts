import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { movieReadDto } from '../entity/movieReadDto';
import { MoviePageableDto } from '../entity/MoviePageableDto';
import { movieUpdatedDto } from '../entity/movieUpdatedDto';

@Injectable({
  providedIn: 'root'
})
export class AdminProfileService {

  movieBaseUrl = 'http://localhost:8080/movie'

  constructor(private httpClient: HttpClient) { }

  getAllMovie(pageNo: number): Observable<MoviePageableDto>{
    return this.httpClient.get<MoviePageableDto>(`${this.movieBaseUrl}`+'?pageNo='+pageNo)
  }

  deleteMovieById(id: number):Observable<string>{
    return this.httpClient.delete<string>(`${this.movieBaseUrl}`+"/"+id, { responseType: 'text' as 'json' })
  }

  updateMovie(updatedMovie: movieUpdatedDto, id: number):Observable<string>{
    return this.httpClient.put<string>(`${this.movieBaseUrl}`+"/update/"+id,updatedMovie, { responseType: 'text' as 'json' })
  }
}
