import { Component, OnInit } from '@angular/core';
import { movieReadDto } from '../../entity/movieReadDto';
import { MovieSearchService } from '../../service/movie-search.service';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { FormsModule, NgModelGroup } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-movie-search',
  standalone: true,
  imports: [CommonModule,RouterModule,FormsModule],
  templateUrl: './movie-search.component.html',
  styleUrl: './movie-search.component.css'
})
export class MovieSearchComponent implements OnInit{

  searchedMovie: movieReadDto[] = []
  text: string = ''
  wordNumber: number = 80
  constructor(private movieSearchService: MovieSearchService, private route: ActivatedRoute){

  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.text = params['text'] || ''; 
      
      if (this.text) {
        this.searchMovie(this.text);
      }
    });
  }

  searchMovie(query: string){
    
    this.movieSearchService.searchMovie(query).subscribe(response =>{this.searchedMovie = response })

  }
  public reducDescriptionSize(description: String){
    if (description.length > this.wordNumber) {
      const truncatedWords = description.slice(0, this.wordNumber);
      return description.slice(0, this.wordNumber) + " ...";
  }
  return description;
  }

   
}
