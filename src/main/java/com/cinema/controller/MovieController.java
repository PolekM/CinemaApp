package com.cinema.controller;

import com.cinema.dto.movie.MovieReadDto;
import com.cinema.dto.movie.MovieUpdateDto;
import com.cinema.dto.movie.MovieSaveDto;
import com.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping()
    public List<MovieReadDto> getAllMovie(){
        return movieService.getAllMovie();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewMovie(@RequestBody MovieSaveDto movieSaveDto){
        return movieService.addNewMovie(movieSaveDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMovie(@RequestBody MovieUpdateDto movieUpdateDto, @PathVariable Integer id){
        return movieService.updateMovie(movieUpdateDto,id);
    }
}
