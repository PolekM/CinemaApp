import { Component, OnInit } from '@angular/core';
import { movieReadDto } from '../../entity/movieReadDto';
import { MovieService } from '../../service/movie.service';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { MovieDetailsComponent } from '../movie-details/movie-details.component';
import { MoviePageableDto } from '../../entity/MoviePageableDto';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-movie',
  standalone: true,
  imports: [CommonModule,RouterModule,MovieDetailsComponent,FormsModule],
  templateUrl: './movie.component.html',
  styleUrl: './movie.component.css'
})
export class MovieComponent implements OnInit {

   movies: MoviePageableDto = {} as MoviePageableDto
   private wordNumber: number = 80;
   text: string = ''

  constructor(private movieService: MovieService, private router: Router){

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
  public searchMovie(){
    this.router.navigate(['/movie/search'],{queryParams: {text: this.text}})
  }
  


}
