import { Component, OnInit } from '@angular/core';
import { UserReadDto } from '../../entity/UserReadDto';
import { UserProfileService } from '../../service/user-profile.service';
import { ChangePasswordDto } from '../../entity/ChangePasswordDto';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { LoginService } from '../../service/login.service';

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent implements OnInit {

  user: UserReadDto = {} as UserReadDto
  changePasswordDto: ChangePasswordDto = {} as ChangePasswordDto
  message: string =''

  constructor(private userProfileService: UserProfileService, private loginService: LoginService){

  }
  ngOnInit(): void {
    this.getUserData()
  }
  getUserData(){
    this.userProfileService.getUserData().subscribe(Response => this.user =Response)
  }

  changePassword(){
    this.userProfileService.changePassword(this.changePasswordDto).subscribe(Response => 
      {
      this.message = Response
      this.loginService.logoutUser()
    })
  }
  
}
