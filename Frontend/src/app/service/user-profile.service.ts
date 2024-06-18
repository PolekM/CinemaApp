import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserReadDto } from '../entity/UserReadDto';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {

 private url = 'http://localhost:8080/'

  constructor(private http: HttpClient) { }

  public getUserData(): Observable<UserReadDto>{
    return this.http.get<UserReadDto>(`${this.url}`+'profile/data')
  }
}
