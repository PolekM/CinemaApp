package com.cinema.controller;

import com.cinema.dto.movie.MoviePageableDto;
import com.cinema.dto.movie.MovieReadDto;
import com.cinema.dto.movie.MovieUpdateDto;
import com.cinema.dto.movie.MovieSaveDto;
import com.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/movie")
@CrossOrigin(origins = "http://localhost:4200")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping()
    public MoviePageableDto getAllMovie(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "8", required = false) int pageSize) {
        return movieService.getAllMovie(pageNo, pageSize);
    }


    @GetMapping("/{id}")
    public MovieReadDto getMovieById(@PathVariable(name = "id") int movieId) {
        return movieService.getMovieById(movieId);
    }


    @PostMapping("/add")
    public ResponseEntity<String> addNewMovie(@RequestBody MovieSaveDto movieSaveDto) {
        return movieService.addNewMovie(movieSaveDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMovie(@RequestBody MovieUpdateDto movieUpdateDto, @PathVariable Integer id) {
        return movieService.updateMovie(movieUpdateDto, id);
    }

    @GetMapping("/search")
    public List<MovieReadDto> movieSearch(@RequestParam String text) {
        return movieService.movieSearch(text);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovieById(@PathVariable(name = "id") Integer id){
        return movieService.deleteMovieById(id);
    }
}
