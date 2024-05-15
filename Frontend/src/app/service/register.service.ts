import { Injectable } from '@angular/core';
import { RegisterSaveDto } from '../entity/RegisterSaveDto';
import { Observable, catchError, map, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Route, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  baseUrl: String = 'http://localhost:8080'

  constructor(private http: HttpClient, private router:Router) { }

  registerUser(registerSaveDto: RegisterSaveDto){
    console.log("test")
      return this.http.post(`${this.baseUrl}`+'/register',registerSaveDto, {responseType: "text"}).subscribe(
        response =>{
          this.router.navigateByUrl('/movie');
        },
        error =>{
            console.log(error);
        }
      );
          

    }

  }

