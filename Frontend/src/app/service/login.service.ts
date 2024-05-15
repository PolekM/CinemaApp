import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { loginWriteDto } from '../entity/loginWriteDto';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loginUrl = 'http://localhost:8080/custom-login'



  constructor(private http: HttpClient, private router: Router) { }

  isLoginSubject = new BehaviorSubject<Boolean>(this.hasToken())

  authUser(loginWriteDto: loginWriteDto){
    const headers = new HttpHeaders(
      {
        'Authorization': 'Basic ' + btoa(`${loginWriteDto.login}:${loginWriteDto.password}`),
        
      });
      const body = {
        login: loginWriteDto.login,
        password: loginWriteDto.password
      };
      this.http.post(`${this.loginUrl}`,body, {responseType: "text"}).subscribe(
        response => {
          let authString = 'Basic ' + btoa(`${loginWriteDto.login}:${loginWriteDto.password}`);
          localStorage.setItem('authorization',authString);
          localStorage.setItem('userRole',response);
          this.isLoginSubject.next(true);
          console.log("login: "+this.isLoginSubject.getValue)
          this.router.navigateByUrl('/movie');
        },
        error => {
          console.log("nie zalogowano")
    
        }
      );
      
  }
  logoutUser(){
    localStorage.removeItem('authorization');
    localStorage.removeItem('userRole');  
    this.isLoginSubject.next(false);
    this.router.navigate(['/login']);
  }

  hasToken(): Boolean{
    return localStorage.getItem('authorization')==null? false:true;
  }
  
  isLoggedIn() : Observable<Boolean> {
    return this.isLoginSubject.asObservable();
   }

}
