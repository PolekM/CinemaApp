import { Component, OnInit } from '@angular/core';
import { AppUserService } from '../../service/app-user.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-app-user',
  standalone: true,
  imports: [],
  templateUrl: './app-user.component.html',
  styleUrl: './app-user.component.css'
})
export class AppUserComponent implements OnInit{
[x: string]: any;
  message: String 
  constructor(private appUserService: AppUserService, private router:Router){
    this.message ="";
  }
  ngOnInit(): void {
  }

    




}
