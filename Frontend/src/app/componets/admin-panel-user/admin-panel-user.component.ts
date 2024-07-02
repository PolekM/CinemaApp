import { Component, OnInit } from '@angular/core';
import { AdminProfileService } from '../../service/admin-profile.service';
import { UserListDto } from '../../entity/UserListDto';
import { FormsModule } from '@angular/forms';
import { appRole } from '../../entity/AppRole';
import { UserChangeRoleDto } from '../../entity/UserChangeRoleDto';

@Component({
  selector: 'app-admin-panel-user',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './admin-panel-user.component.html',
  styleUrl: './admin-panel-user.component.css'
})
export class AdminPanelUserComponent implements OnInit {

  userList: UserListDto[] = []
  editedUser: UserListDto = {appRole: {}} as UserListDto
  userRole: appRole[] = [] 
  msg: String =''

  constructor(private adminService: AdminProfileService){

  }
  ngOnInit(): void {
    this.getAllUsers()
    if(this.userRole.length==0){
      this.getUserRole()
    }
  
  }

  getAllUsers(){
    this.adminService.getAllUsers().subscribe(response =>{this.userList = response})
  }

  setEditUser(user: UserListDto){
    this.editedUser = user;
  }

  getUserRole(){
    this.adminService.getUserRole().subscribe(response => this.userRole = response)
  }
  updateRole(){
   let updatedUser: UserChangeRoleDto = {
    id: this.editedUser.id,
    appRole: this.editedUser.appRole
   } 
   this.adminService.updateUserRole(updatedUser).subscribe(response => this.msg = response)
  }
  
}
