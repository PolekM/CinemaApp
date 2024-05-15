import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppUserService {

 private url = 'http://localhost:8080/admin';

  constructor(private http: HttpClient) { }

  getTest(): Observable<String>{
    return this.http.get(`${this.url}`,{responseType: "text"});
    
  }
}
