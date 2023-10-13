package de.neuefische.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepo movieRepo;

    public List<Movie> getAllMovies () {
        return movieRepo.findAll();
    }

    public Movie getMovieById (String id) throws NoSuchElementException {
        return movieRepo.findById(id).orElseThrow();
    }

    public Movie putMovieById (String id, Movie movie) {
        /*
            Take in a whole Movie object, already containing all the desired attributes.
            Then use the (already existing) id to exchange its contents in the DB:
        */
        Movie updatedMovie = new Movie(
                id,
                movie.getTitle(),
                movie.getYear(),
                movie.getExtract(),
                movie.getThumbnail(),
                movie.getIsFavorite() // The only actually new value!
        );
        return movieRepo.save(updatedMovie);
    }

    public Movie toggleIsFavorite(String id, boolean favoriteStatement) throws IllegalArgumentException{
        Optional<Movie> updatedMovie = movieRepo.findById(id);

        if (updatedMovie.isPresent()) {
            return movieRepo.save(updatedMovie.get().withFavorite(favoriteStatement));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String deleteMovieById(String id) {
       movieRepo.deleteById(id);
       return "Movie with id: "+ id + " was deleted.";
    }
}
