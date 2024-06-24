import { Component, Input } from '@angular/core';
import { AdminPanelButtonComponent } from '../admin-panel-button/admin-panel-button.component';
import { RouterOutlet } from '@angular/router';
import { AdminPanelUserComponent } from '../admin-panel-user/admin-panel-user.component';
import { AdminPanelSeanceComponent } from '../admin-panel-seance/admin-panel-seance.component';
import { AdminPanelMovieComponent } from '../admin-panel-movie/admin-panel-movie.component';

@Component({
  selector: 'app-admin-panel',
  standalone: true,
  imports: [AdminPanelButtonComponent, AdminPanelSeanceComponent,AdminPanelUserComponent,AdminPanelMovieComponent],
  templateUrl: './admin-panel.component.html',
  styleUrl: './admin-panel.component.css'
})
export class AdminPanelComponent {

  @Input() currentComponents: string

  constructor(){
    this.currentComponents = "seance"
  }

  handleEventFromAdminButtonComponent(data: string){
    this.currentComponents = data
  }
}
