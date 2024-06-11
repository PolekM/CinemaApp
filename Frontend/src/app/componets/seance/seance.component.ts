import { Component, EventEmitter, NgModule, OnInit, Output } from '@angular/core';
import { SeanceService } from '../../service/seance.service';
import { CommonModule, DatePipe } from '@angular/common';
import { SeanceReadWithStartTimeListDto } from '../../entity/SeanceReadWithStartTimeListDto';
import { MovieDetailsComponent } from '../movie-details/movie-details.component';
import { RouterModule } from '@angular/router';
import { DatePickerComponent } from '../date-picker/date-picker.component';
import { SeanceListComponent } from '../seance-list/seance-list.component';


@Component({
  selector: 'app-seance',
  standalone: true,
  imports: [CommonModule,RouterModule,MovieDetailsComponent,DatePickerComponent, SeanceListComponent],
  templateUrl: './seance.component.html',
  styleUrl: './seance.component.css'
})
export class SeanceComponent{
  
  selectedDate: string
  constructor(){
    this.selectedDate = new Date().toISOString().split('T')[0];
    
  }

  onDateChange(date: string) {
    this.selectedDate = date;
  }
  
}
