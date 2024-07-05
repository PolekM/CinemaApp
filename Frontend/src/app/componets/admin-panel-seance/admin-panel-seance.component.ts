import { Component, OnInit } from '@angular/core';
import { AdminProfileService } from '../../service/admin-profile.service';
import { seanceReadDto } from '../../entity/seanceReadDto';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin-panel-seance',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './admin-panel-seance.component.html',
  styleUrl: './admin-panel-seance.component.css'
})
export class AdminPanelSeanceComponent implements OnInit {

  seances: seanceReadDto[] = []
  editedSeance: seanceReadDto = {} as seanceReadDto

  constructor(private adminService: AdminProfileService){

  }

  ngOnInit(): void {
    this.getAllSeance()
  }

  getAllSeance(){
    this.adminService.getAllSeance().subscribe(response => this.seances =response)
    console.log(this.seances)
  }

  setEditedSeance(seance: seanceReadDto){
    this.editedSeance = seance;
  }


}
