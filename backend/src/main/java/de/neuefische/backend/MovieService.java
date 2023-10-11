package de.neuefische.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public Movie putMovieById (String id, Movie movie) throws DataAccessException {
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

}
