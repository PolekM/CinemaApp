import { DatePipe } from '@angular/common';
import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-date-picker',
  standalone: true,
  imports: [],
  templateUrl: './date-picker.component.html',
  styleUrl: './date-picker.component.css'
})
export class DatePickerComponent {

  @Output() dateChange: EventEmitter<String> = new EventEmitter<String>();
  currentDate:String

  constructor(private datePipe: DatePipe){
    this.currentDate = this.getCurrentDate();
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
     }
     if(changeNumber === 1){
       const currentDate = new Date(parseInt(year), parseInt(month) - 1, parseInt(day)+1);
       this.currentDate = this.datePipe.transform(currentDate, 'YYYY-MM-dd') || '';
     }
     this.dateChange.emit(this.currentDate);
   }
 
}
