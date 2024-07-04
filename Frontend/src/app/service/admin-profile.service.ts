import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MoviePageableDto } from '../entity/MoviePageableDto';
import { movieUpdatedDto } from '../entity/movieUpdatedDto';
import { UserListDto } from '../entity/UserListDto';
import { appRole } from '../entity/AppRole';
import { UserChangeRoleDto } from '../entity/UserChangeRoleDto';
import { seanceReadDto } from '../entity/seanceReadDto';

@Injectable({
  providedIn: 'root'
})
export class AdminProfileService {

  movieBaseUrl = 'http://localhost:8080/movie'
  userBaseUrL = 'http://localhost:8080/profile'
  seanceBaseUrl = 'http://localhost:8080/seance'

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

  getAllUsers(): Observable<UserListDto[]>{
    return this.httpClient.get<UserListDto[]>(`${this.userBaseUrL}`+'/users')
  }
  getUserRole(): Observable<appRole[]>{
    return this.httpClient.get<appRole[]>(`${this.userBaseUrL}`+'/role')
  }

  updateUserRole(user: UserChangeRoleDto):Observable<String>{
    return this.httpClient.put<String>(`${this.userBaseUrL}`+"/role/change",user, { responseType: 'text' as 'json' })
  }

  getAllSeance():Observable<seanceReadDto[]>{
    return this.httpClient.get<seanceReadDto[]>(`${this.seanceBaseUrl}`)
  }
}
