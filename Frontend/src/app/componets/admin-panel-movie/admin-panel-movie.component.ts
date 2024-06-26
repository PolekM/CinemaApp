import { Component, OnInit } from '@angular/core';
import { MoviePageableDto } from '../../entity/MoviePageableDto';
import { AdminProfileService } from '../../service/admin-profile.service';
import { Router } from '@angular/router';
import { movieReadDto } from '../../entity/movieReadDto';

@Component({
  selector: 'app-admin-panel-movie',
  standalone: true,
  imports: [],
  templateUrl: './admin-panel-movie.component.html',
  styleUrl: './admin-panel-movie.component.css'
})
export class AdminPanelMovieComponent implements OnInit {

  movies: MoviePageableDto = {} as MoviePageableDto;
  msg: string =''
  editedMovie: movieReadDto = {} as movieReadDto

  constructor(private adminService: AdminProfileService, private router: Router){

  }
  ngOnInit(): void {
    this.getAllMovie(0);
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
}
