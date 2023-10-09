package de.neuefische.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    MovieRepo movieRepo;

    @Test
    @DirtiesContext
    void getAllMovies_expectEmptyList() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                    []
                """
                ));
    }

    @Test
    @DirtiesContext
    void getAllMovies_expectMovies() throws Exception {

        movieRepo.save(new Movie("adalij039q", "LOTR - die Gefährten", "Peter Jackson"));
        movieRepo.save(new Movie("adalij039r", "LOTR - die zwei Türme", "Peter Jackson"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                    [
                        {
                            "_id": "adalij039q",
                            "title": "LOTR - die Gefährten",
                            "director": "Peter Jackson"
                        },
                        {
                            "_id": "adalij039r",
                            "title": "LOTR - die zwei Türme",
                            "director": "Peter Jackson"
                        }
                    ]
                """
                ));
    }

    @Test
    @DirtiesContext
    void getMovieById_expectMovie() throws Exception {
        Movie m1 = movieRepo.save(new Movie("adalij039q", "LOTR - die Gefährten", "Peter Jackson"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/" + m1._id()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                            "_id": "adalij039q",
                            "title": "LOTR - die Gefährten",
                            "director": "Peter Jackson"
                        }
                """
                ));
    }

    @Test
    @DirtiesContext
    void getMovieById_expectNoSuchElementException() throws Exception {
        //Movie m1 = movieRepo.save(new Movie("adalij039q", "LOTR - die Gefährten", "Peter Jackson"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/" + "quatschId"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        "Ups, hier ist etwas schief gelaufen..."
                ));
    }


}