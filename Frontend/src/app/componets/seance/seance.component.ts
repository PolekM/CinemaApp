import { Component, EventEmitter, NgModule, OnInit, Output } from '@angular/core';
import { SeanceService } from '../../service/seance.service';
import { CommonModule, DatePipe } from '@angular/common';
import { SeanceReadWithStartTimeListDto } from '../../entity/SeanceReadWithStartTimeListDto';
import { MovieDetailsComponent } from '../movie-details/movie-details.component';
import { RouterModule } from '@angular/router';
import { DatePickerComponent } from '../date-picker/date-picker.component';


@Component({
  selector: 'app-seance',
  standalone: true,
  imports: [CommonModule,RouterModule,MovieDetailsComponent,DatePickerComponent],
  templateUrl: './seance.component.html',
  styleUrl: './seance.component.css'
})
export class SeanceComponent implements OnInit{

 
  
  seanceByDate: SeanceReadWithStartTimeListDto[] = []
  constructor(private seanceService: SeanceService, private datePipe: DatePipe){
  
    
  }
  ngOnInit(): void {
    this.getSeanceByDate(new Date().toISOString().split('T')[0]);
  
  }

  getSeanceByDate(date:String){
    this.seanceService.getSeanceByDate(date).subscribe(data =>{this.seanceByDate = data})
  }

  onDateChange(date: String) {
    this.getSeanceByDate(date);
  }
  
}
