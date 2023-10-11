package de.neuefische.backend;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    MovieRepo movieRepo = mock(MovieRepo.class);
    MovieService movieService = new MovieService(movieRepo);

    Movie m1 = new Movie(
            "65250133a87cf67dc7b57cdd",
            "The Grudge",
            2020,
            "The Grudge is a 2020 American psychological supernatural horror film...",
            "https://upload.wikimedia.org/wikipedia/en/3/34/The_Grudge_2020_Poster.jpeg"
    );

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

        /*
            String _id,
            String title,
            int year,
            String extract,
            String thumbnail
         */


        //GIVEN

        List<Movie> movieList = List.of(m1);

        //WHEN
        when(movieRepo.findAll()).thenReturn(movieList);
        List<Movie> actual = movieService.getAllMovies();

        //THEN
        List<Movie> expected = List.of(new Movie(

                        "65250133a87cf67dc7b57cdd",
                        "The Grudge",
                        2020,
                        "The Grudge is a 2020 American psychological supernatural horror film...",
                        "https://upload.wikimedia.org/wikipedia/en/3/34/The_Grudge_2020_Poster.jpeg"
                )
        );

        verify(movieRepo).findAll();
        assertEquals(expected, actual);
    }


    @Test
    void getMovieById_expectLOTR() {

        //GIVEN
        String id = m1.get_id();

        //WHEN
        when(movieRepo.findById(id)).thenReturn(Optional.of(m1));
        Movie actual = movieService.getMovieById(id);

        //THEN
        Movie expected = new Movie(

                "65250133a87cf67dc7b57cdd",
                "The Grudge",
                2020,
                "The Grudge is a 2020 American psychological supernatural horror film...",
                "https://upload.wikimedia.org/wikipedia/en/3/34/The_Grudge_2020_Poster.jpeg"
        );

        verify(movieRepo).findById(id);
        assertEquals(expected, actual);

    }

    @Test
    void getMovieById_expectNoSuchElementException() {
        // GIVEN
        String id = "currywurst"; // Im Grunde egal was hier steht (brauchen wir nur, um überhaupt einen Übergabewert zu haben)

        // WHEN
        when(movieRepo.findById(id)).thenReturn(Optional.empty());
        /*
            Um Fehlschlagen des Tests zu provozieren z.B. den thenReturn() befüllen mit:
            Optional.of( <gültigesMovieObjekt> )
        */

        // THEN
        // Grundsätzlich: Exceptions treten immer an die Stelle des Methoden-returns,
        // deshalb hier Kernelement unseres Tests: Exception fangen!
        // Möglichkeiten dafür:
        // 1. Try Catch (geht immer/überall im Code)
        // 2. assertThrows
        assertThrows(NoSuchElementException.class, () -> movieService.getMovieById(id));
    }

}

