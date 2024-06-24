import { Component } from '@angular/core';
import { AdminPanelButtonComponent } from '../admin-panel-button/admin-panel-button.component';

@Component({
  selector: 'app-admin-panel',
  standalone: true,
  imports: [AdminPanelButtonComponent],
  templateUrl: './admin-panel.component.html',
  styleUrl: './admin-panel.component.css'
})
export class AdminPanelComponent {

}
