import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-app-user',
  standalone: true,
  imports: [NgbModule],
  templateUrl: './app-user.component.html',
  styleUrl: './app-user.component.css'
})
export class AppUserComponent implements OnInit{
  constructor(private router:Router){

  }
  ngOnInit(): void {
  }

    




}
