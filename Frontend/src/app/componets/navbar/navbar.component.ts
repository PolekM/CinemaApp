import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../service/login.service';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { Router, RouterModule } from '@angular/router';






@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule,NgbModule,RouterModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent{

  isLoggedIn : Observable<Boolean>;
  isAdmin: Observable<Boolean>;
  isNavbarCollapsed = true;

  constructor(private loginService: LoginService){
    this.isLoggedIn = loginService.isLoggedIn()
    this.isAdmin = loginService.isLoggedAsAdmin()
  }


  logoutUser(){
    this.loginService.logoutUser();
  }
}
