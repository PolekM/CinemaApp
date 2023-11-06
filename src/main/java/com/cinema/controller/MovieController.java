package com.cinema.controller;

import com.cinema.dto.MovieDto;
import com.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public List<MovieDto> getAllMovie(){
        return movieService.getAllMovie();
    }

    @PostMapping("/add")
    public MovieDto addNewMovie(@RequestBody MovieDto movieDto){
        return movieService.addNewMovie(movieDto);
    }

}
