import { Component, OnInit } from '@angular/core';
import { UserReadDto } from '../../entity/UserReadDto';
import { UserProfileService } from '../../service/user-profile.service';

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent implements OnInit {

  user: UserReadDto = {} as UserReadDto

  constructor(private userProfileService: UserProfileService){

  }
  ngOnInit(): void {
    this.getUserData()
  }
  getUserData(){
    this.userProfileService.getUserData().subscribe(Response => this.user =Response)
  }
  
}
