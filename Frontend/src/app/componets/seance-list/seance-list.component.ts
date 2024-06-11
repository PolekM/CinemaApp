import { Component, Input, NgModule, SimpleChanges } from '@angular/core';
import { SeanceReadWithStartTimeListDto } from '../../entity/SeanceReadWithStartTimeListDto';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SeanceService } from '../../service/seance.service';

@Component({
  selector: 'app-seance-list',
  standalone: true,
  imports: [RouterLink,CommonModule],
  templateUrl: './seance-list.component.html',
  styleUrl: './seance-list.component.css'
})
export class SeanceListComponent {
  @Input() date: string = ''
  seances: SeanceReadWithStartTimeListDto[] | undefined;

  constructor(private seanceService: SeanceService) { }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['date'] && this.date) {
      this.getSeanceByDate(this.date);
    }
  }

  getSeanceByDate(date: string) {
    this.seanceService.getSeanceByDate(date).subscribe(data => {
      this.seances = data;
    });
  }
}
