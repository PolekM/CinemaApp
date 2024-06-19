import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserReadDto } from '../entity/UserReadDto';
import { HttpClient } from '@angular/common/http';
import { ChangePasswordDto } from '../entity/ChangePasswordDto';

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {

 private url = 'http://localhost:8080/'

  constructor(private http: HttpClient) { }

  public getUserData(): Observable<UserReadDto>{
    return this.http.get<UserReadDto>(`${this.url}`+'profile/data')
  }

  public changePassword(changePasswordDto: ChangePasswordDto): Observable<string>{
    return this.http.post<string>(`${this.url}`+'profile/change-password',changePasswordDto, {responseType: 'text' as 'json'})
  }
}
