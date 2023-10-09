package de.neuefische.backend;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movies")
    public List<Movie> getAllMovies () {
        return movieService.getAllMovies();
    }

    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable String id) throws NoSuchElementException{
        return movieService.getMovieById(id);
    }


    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException() {
        return "Ups, hier ist etwas schief gelaufen...";
    }

}
