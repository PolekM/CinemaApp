import { Component, OnInit } from '@angular/core';
import { movieReadDto } from '../../entity/movieReadDto';
import { MovieService } from '../../service/movie.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MovieDetailsComponent } from '../movie-details/movie-details.component';
import { MoviePageableDto } from '../../entity/MoviePageableDto';


@Component({
  selector: 'app-movie',
  standalone: true,
  imports: [CommonModule,RouterModule,MovieDetailsComponent],
  templateUrl: './movie.component.html',
  styleUrl: './movie.component.css'
})
export class MovieComponent implements OnInit {

   movies: MoviePageableDto = {} as MoviePageableDto
   private wordNumber: number = 80;

  constructor(private movieService: MovieService){

  }
  ngOnInit(): void {
    this.getMovie(0);
  }

  public getMovie(pageNo:number){
    this.movies.currentPage = pageNo
    this.movieService.getMovie(pageNo).subscribe(data =>{this.movies= data})
  }

  public reducDescriptionSize(description: String){

    

    if (description.length > this.wordNumber) {
      const truncatedWords = description.slice(0, this.wordNumber);
      return description.slice(0, this.wordNumber) + " ...";
  }
  return description;


  }

  


}
