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

    @Test
    void updateMovieById_expectUpdatedMovieObject(){

        //GIVEN
        Movie m1Update = new Movie(m1.get_id(), m1.getTitle(), m1.getYear(), m1.getExtract(), m1.getThumbnail(), true);

        //WHEN
        when(movieRepo.save(m1Update)).thenReturn(m1Update);
        Movie actual = movieService.putMovieById(m1.get_id(), m1Update);

        //THEN
        Movie expected = new Movie(

                "65250133a87cf67dc7b57cdd",
                "The Grudge",
                2020,
                "The Grudge is a 2020 American psychological supernatural horror film...",
                "https://upload.wikimedia.org/wikipedia/en/3/34/The_Grudge_2020_Poster.jpeg",
                true
        );

        verify(movieRepo).save(m1Update);
        assertEquals(expected, actual);
    }

    @Test
    void toggleIsFavorite_expectIsFavoriteHasChangedToTrue(){

        //GIVEN
        Movie m1Update = new Movie(m1.get_id(), m1.getTitle(), m1.getYear(), m1.getExtract(), m1.getThumbnail(), true);

        //WHEN
        when(movieRepo.findById(m1.get_id())).thenReturn(Optional.of(m1));
        when(movieRepo.save(m1Update)).thenReturn(m1Update);
        Movie actual = movieService.toggleIsFavorite(m1.get_id(), m1Update.getIsFavorite());

        //THEN
        Movie expected = new Movie(

                "65250133a87cf67dc7b57cdd",
                "The Grudge",
                2020,
                "The Grudge is a 2020 American psychological supernatural horror film...",
                "https://upload.wikimedia.org/wikipedia/en/3/34/The_Grudge_2020_Poster.jpeg",
                true
        );

        verify(movieRepo).save(m1Update);
        assertEquals(expected, actual);
    }

    @Test
    void toggleIsFavorite_expectIllegalArgumentException(){

        //GIVEN
        String id = "quatschId";
        Movie m1Update = new Movie(id, m1.getTitle(), m1.getYear(), m1.getExtract(), m1.getThumbnail(), true);
        boolean updateFavorite = m1Update.getIsFavorite();

        //WHEN
        when(movieRepo.findById(id)).thenReturn(Optional.empty());
        when(movieRepo.save(m1Update)).thenReturn(m1Update);

        //THEN
        assertThrows(IllegalArgumentException.class, () -> movieService.toggleIsFavorite(id, updateFavorite));
    }

    @Test
    void deleteMovieById_expectDeleteMessage() {
        //GIVEN
        movieRepo.save(m1);
        String id = "65250133a87cf67dc7b57cdd";

        //WHEN
        when(movieRepo.existsById(id)).thenReturn(true);
        doNothing().when(movieRepo).deleteById(id);
        movieService.deleteMovieById(id);

        // THEN
        verify(movieRepo, times(1)).deleteById(id);
    }

    @Test
    void deleteMovieById_expectIdNotFoundMessage() {
        //GIVEN
        String id = "quatschId";

        //WHEN
        when(movieRepo.existsById(id)).thenReturn(false);
        movieRepo.deleteById(id);
        String actual = movieService.deleteMovieById(id);
        String expected = "die ID existiert nicht";

        // THEN
        verify(movieRepo, times(1)).deleteById(id);
        assertEquals(expected, actual);
    }



}

