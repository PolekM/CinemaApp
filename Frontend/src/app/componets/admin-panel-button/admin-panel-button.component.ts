import { Component, EventEmitter, Output } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-admin-panel-button',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './admin-panel-button.component.html',
  styleUrl: './admin-panel-button.component.css'
})
export class AdminPanelButtonComponent {
  @Output() currentComponent: EventEmitter<string> = new EventEmitter<string>();

  constructor(){

  }

  clickedButton(buttonText: string){
    this.currentComponent.emit(buttonText)
  }

}
