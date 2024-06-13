import { Component,OnInit } from '@angular/core';
import { movieReadDto } from '../../entity/movieReadDto';
import { MovieService } from '../../service/movie.service';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { SeanceListComponent } from '../seance-list/seance-list.component';
import { SeanceService } from '../../service/seance.service';
import { SeanceReadWithStartTimeListDto } from '../../entity/SeanceReadWithStartTimeListDto';


@Component({
  selector: 'app-movie-details',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './movie-details.component.html',
  styleUrl: './movie-details.component.css'
})
export class MovieDetailsComponent implements OnInit {
 movie: movieReadDto = {} as movieReadDto;
 nearestSeance?: SeanceReadWithStartTimeListDto 
 movieId: number = {} as number;
 constructor(private movieService: MovieService,private seanceService:SeanceService, private route: ActivatedRoute){

 }
  ngOnInit(): void {
    this.route.params.subscribe(params =>{this.movieId = params['id']})
    this.getMovieById(this.movieId)
    this.getNearestSeance(this.movieId)
    
  }
 

 public getMovieById(id:number){
  
  return this.movieService.getMovieById(id).subscribe(response =>{this.movie = response});
 }
 public getNearestSeance(id:number){
  return this.seanceService.getNearestSeance(id).subscribe(response =>{this.nearestSeance = response})
 }
}



