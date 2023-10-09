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
}
