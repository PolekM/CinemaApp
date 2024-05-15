import { Component, EventEmitter, NgModule, OnInit, Output } from '@angular/core';
import { SeanceService } from '../../service/seance.service';
import { CommonModule, DatePipe } from '@angular/common';
import { SeanceReadWithStartTimeListDto } from '../../entity/SeanceReadWithStartTimeListDto';
import { MovieDetailsComponent } from '../movie-details/movie-details.component';
import { RouterModule } from '@angular/router';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-seance',
  standalone: true,
  imports: [CommonModule,RouterModule,MovieDetailsComponent],
  templateUrl: './seance.component.html',
  styleUrl: './seance.component.css'
})
export class SeanceComponent implements OnInit{

  currentDate: String;
 
  
  seanceByDate: SeanceReadWithStartTimeListDto[] = []
  constructor(private seanceService: SeanceService, private datePipe: DatePipe){
    this.currentDate = this.getCurrentDate();
    
  }
  ngOnInit(): void {
   this.getSeanceByDate(this.currentDate);
  
  }

  getSeanceByDate(date:String){
    this.seanceService.getSeanceByDate(date).subscribe(data =>{this.seanceByDate = data})
  }
  getCurrentDate(): String{
   const currentDate = new Date();
   const formattedDate = this.datePipe.transform(currentDate, 'YYYY-MM-dd') || '';
   return formattedDate
  }

  changeDate(changeNumber: number){
    const [year, month, day] = this.currentDate.split('-');
    if(changeNumber === -1){
      const currentDate = new Date(parseInt(year), parseInt(month) - 1, parseInt(day)-1);
      this.currentDate = this.datePipe.transform(currentDate, 'YYYY-MM-dd') || '';
     
      this.getSeanceByDate(this.currentDate);

    }
    if(changeNumber === 1){
      const currentDate = new Date(parseInt(year), parseInt(month) - 1, parseInt(day)+1);
      this.currentDate = this.datePipe.transform(currentDate, 'YYYY-MM-dd') || '';
   
      this.getSeanceByDate(this.currentDate);
    }
  }

}
