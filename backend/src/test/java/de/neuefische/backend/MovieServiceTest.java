package de.neuefische.backend;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    MovieRepo movieRepo = mock(MovieRepo.class);
    MovieService movieService = new MovieService(movieRepo);

    @Test
    void getAllMovies_expectEmptyList() {

        //GIVEN
        List<Movie> movieList = List.of();

        //WHEN
        when(movieRepo.findAll()).thenReturn(movieList);
        List<Movie> actual = movieService.getAllMovies();

        //THEN
        List<Movie> expected = List.of();

        verify(movieRepo).findAll();
        assertEquals(expected, actual);
    }

    @Test
    void getAllMovies_expectOneMovie() {

        //GIVEN
        Movie m1 = new Movie("adalij039q", "LOTR - die Gefährten", "Peter Jackson");
        List<Movie> movieList = List.of(m1);

        //WHEN
        when(movieRepo.findAll()).thenReturn(movieList);
        List<Movie> actual = movieService.getAllMovies();

        //THEN
        List<Movie> expected = List.of(new Movie("adalij039q", "LOTR - die Gefährten", "Peter Jackson"));

        verify(movieRepo).findAll();
        assertEquals(expected, actual);
    }


    @Test
    void getMovieById_expectLOTR() {

        //GIVEN
        Movie m1 = new Movie("adalij039q", "LOTR - die Gefährten", "Peter Jackson");
        String id = m1._id();

        //WHEN
        when(movieRepo.findById(id)).thenReturn(Optional.of(m1));
        Movie actual = movieService.getMovieById(id);

        //THEN
        Movie expected = new Movie("adalij039q", "LOTR - die Gefährten", "Peter Jackson");
        verify(movieRepo).findById(id);
        assertEquals(expected, actual);

    }


}

