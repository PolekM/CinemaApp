import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppUserService } from '../../service/app-user.service';
import { SeanceOnScreenDto } from '../../entity/SeanceOnScreenDto';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-app-user',
  standalone: true,
  imports: [NgbModule,FormsModule,CommonModule],
  templateUrl: './app-user.component.html',
  styleUrl: './app-user.component.css'
})
export class AppUserComponent implements OnInit{

  seanceOnScreen: SeanceOnScreenDto[] = []

  constructor(private router:Router, private appUserService: AppUserService){

  }
  ngOnInit(): void {
    this.getMovieOnScreen();
  }

  getMovieOnScreen(){
    this.appUserService.getMovieOnScreen().subscribe(Response =>{
      this.seanceOnScreen = Response;
    })
  }

    




}
