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

        movieRepo.save(new Movie(
                "65250133a87cf67dc7b57cdd",
                "The Grudge",
                2020,
                "The Grudge is a 2020 American psychological supernatural horror film...",
                "https://upload.wikimedia.org/wikipedia/en/3/34/The_Grudge_2020_Poster.jpeg"
        ));
        movieRepo.save(new Movie(
                "65250133a87cf67dc7b57cde",
                "Underwater",
                2020,
                "Underwater is a 2020 American science fiction action horror film directed by William Eubank and starring Kristen Stewart, Vincent Cassel, and T.J. Miller. It follows a group of scientists who encounter creatures after an earthquake destroys their underwater drilling station.",
                "https://upload.wikimedia.org/wikipedia/en/4/4a/Underwater_poster.jpeg"
        ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                    [
                        {
                        "_id": "65250133a87cf67dc7b57cdd",
                        "title": "The Grudge",
                        "year": 2020,
                        "extract": "The Grudge is a 2020 American psychological supernatural horror film...",
                        "thumbnail": "https://upload.wikimedia.org/wikipedia/en/3/34/The_Grudge_2020_Poster.jpeg"
                        },
                        {
  
                        "_id": "65250133a87cf67dc7b57cde",
                        "title": "Underwater",
                        "year": 2020,
                        "extract": "Underwater is a 2020 American science fiction action horror film directed by William Eubank and starring Kristen Stewart, Vincent Cassel, and T.J. Miller. It follows a group of scientists who encounter creatures after an earthquake destroys their underwater drilling station.",
                        "thumbnail": "https://upload.wikimedia.org/wikipedia/en/4/4a/Underwater_poster.jpeg"
                        }
                    ]
                """
                ));
    }

    @Test
    @DirtiesContext
    void getMovieById_expectMovie() throws Exception {
        Movie m1 = movieRepo.save(new Movie(
                "65250133a87cf67dc7b57cdd",
                "The Grudge",
                2020,
                "The Grudge is a 2020 American psychological supernatural horror film...",
                "https://upload.wikimedia.org/wikipedia/en/3/34/The_Grudge_2020_Poster.jpeg"
        ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/" + m1._id()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                        "_id": "65250133a87cf67dc7b57cdd",
                        "title": "The Grudge",
                        "year": 2020,
                        "extract": "The Grudge is a 2020 American psychological supernatural horror film...",
                        "thumbnail": "https://upload.wikimedia.org/wikipedia/en/3/34/The_Grudge_2020_Poster.jpeg"
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