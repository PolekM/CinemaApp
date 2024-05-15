import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { RegisterService } from '../../service/register.service';
import { RegisterSaveDto } from '../../entity/RegisterSaveDto';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule,
    RouterModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  registerSaveDto: RegisterSaveDto = {
    login: '',
    password: '',
    repeatPassword: '',
    email: ''
  };

  constructor (private registerService: RegisterService){}

  registerUser(){
    this.registerService.registerUser(this.registerSaveDto)
  }
}
