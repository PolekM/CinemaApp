import { Component,OnInit } from '@angular/core';
import { movieReadDto } from '../../entity/movieReadDto';
import { MovieService } from '../../service/movie.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-movie-details',
  standalone: true,
  imports: [],
  templateUrl: './movie-details.component.html',
  styleUrl: './movie-details.component.css'
})
export class MovieDetailsComponent implements OnInit {
 movie: movieReadDto = {} as movieReadDto;
 movieId: number = {} as number;
 constructor(private movieService: MovieService, private route: ActivatedRoute){

 }
  ngOnInit(): void {
    this.route.params.subscribe(params =>{this.movieId = params['id']})
    this.getMovieById(this.movieId)
  }
 

 public getMovieById(id:number){
  
  return this.movieService.getMovieById(id).subscribe(response =>{this.movie = response});
 }
}



