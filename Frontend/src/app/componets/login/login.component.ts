import { Component } from '@angular/core';
import { loginWriteDto } from '../../entity/loginWriteDto';
import { LoginService } from '../../service/login.service';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule,
    RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  public authDate: loginWriteDto;

  constructor(private loginService: LoginService){
    this.authDate = { login: '', password: '' };
  }

  authUser(){
    this.loginService.authUser(this.authDate);
  }

  logoutUser(){
    this.loginService.logoutUser();
  }
}
