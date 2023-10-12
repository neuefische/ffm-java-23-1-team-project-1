package de.neuefische.backend;


import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable String id) throws NoSuchElementException {
        return movieService.getMovieById(id);
    }


    @PutMapping("/movies/{id}")
    public Movie updateMovieById(@PathVariable String id, @RequestBody Movie movie) {
        return movieService.putMovieById(id, movie);
    }


    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException() {
        return "Ups, hier ist etwas schief gelaufen...";
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleHttpMessageNotReadableException(Exception e) {
        return e.toString();
    }

   /* @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return "You suck!" + e.toString();
    }*/

}
