import { Component, NgModule, OnInit } from '@angular/core';
import { MoviePageableDto } from '../../entity/MoviePageableDto';
import { AdminProfileService } from '../../service/admin-profile.service';
import { Router } from '@angular/router';
import { movieReadDto } from '../../entity/movieReadDto';
import { SpeciesReadDto } from '../../entity/SpeciesReadDto';
import { SpeciesService } from '../../service/species.service';
import { movieUpdatedDto } from '../../entity/movieUpdatedDto';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin-panel-movie',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './admin-panel-movie.component.html',
  styleUrl: './admin-panel-movie.component.css'
})
export class AdminPanelMovieComponent implements OnInit {

  movies: MoviePageableDto = {} as MoviePageableDto;
  msg: string =''
  editedMovie: movieReadDto = {} as movieReadDto
  species: SpeciesReadDto[] = []

  constructor(private adminService: AdminProfileService,private speciesService: SpeciesService, private router: Router){

  }
  ngOnInit(): void {
    this.getAllMovie(0);
    if(this.species.length==0){
      this.getAllSpecies();
    }
  }

  getAllMovie(pageNo: number){
    this.adminService.getAllMovie(pageNo).subscribe(Response =>{this.movies = Response})
  }

  deleteMovieById(id: number){
    this.adminService.deleteMovieById(id).subscribe(Response => {
      this.msg = Response 
      this.ngOnInit()
    })
  }
  setMovieToEdit(item: movieReadDto){
    this.editedMovie = item;
    
  }

  getAllSpecies(){
    this.speciesService.getAllSpecies().subscribe(response => {this.species = response})
  }
 

  updateMovie(){
    let foundSpeciesId = this.species.find(s => s.speciesName === this.editedMovie.speciesName);
    let updatedMovie: movieUpdatedDto = {
      title: this.editedMovie.title,
      description: this.editedMovie.description,
      yearOfProduction: this.editedMovie.yearOfProduction,
      speciesId: foundSpeciesId?.specieId,
      movieUrl: this.editedMovie.movieUrl
    }
    this.adminService.updateMovie(updatedMovie,this.editedMovie.movieId).subscribe()

  }
}
