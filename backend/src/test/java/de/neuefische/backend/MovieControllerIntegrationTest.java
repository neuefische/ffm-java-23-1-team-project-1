package de.neuefische.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.test.context.support.WithMockUser;
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

    @MockBean
    ClientRegistrationRepository clientRegistrationRepository;

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

        mockMvc.perform(MockMvcRequestBuilders.get("/api/movies/" + m1.get_id()))
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


    @Test
    @DirtiesContext
    @WithMockUser
    void updateMovieById_expectUpdatedMovieObject() throws Exception {
        Movie m1 = movieRepo.save(new Movie(
                "65250133a87cf67dc7b57cdd",
                "The Grudge",
                2020,
                "The Grudge is a 2020 American psychological supernatural horror film...",
                "https://upload.wikimedia.org/wikipedia/en/3/34/The_Grudge_2020_Poster.jpeg"
        ));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/movies/" + m1.get_id())
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                            {
                            "_id": "65250133a87cf67dc7b57cdd",
                            "title": "The Grudge",
                            "year": 2020,
                            "extract": "The Grudge is a 2020 American psychological supernatural horror film...",
                            "thumbnail": "https://upload.wikimedia.org/wikipedia/en/3/34/The_Grudge_2020_Poster.jpeg",
                            "isFavorite": true
                            }
                    """)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                        "_id": "65250133a87cf67dc7b57cdd",
                        "title": "The Grudge",
                        "year": 2020,
                        "extract": "The Grudge is a 2020 American psychological supernatural horror film...",
                        "thumbnail": "https://upload.wikimedia.org/wikipedia/en/3/34/The_Grudge_2020_Poster.jpeg",
                        "isFavorite": true
                        }
                        """
                ));
    }

    @Test
    @DirtiesContext
    @WithMockUser
    void putMovieById_expectHttpMessageNotReadableException() throws Exception {
        Movie m1 = movieRepo.save(new Movie(
                "65250133a87cf67dc7b57cdd",
                "The Grudge",
                2020,
                "The Grudge is a 2020 American psychological supernatural horror film...",
                "https://upload.wikimedia.org/wikipedia/en/3/34/The_Grudge_2020_Poster.jpeg"
        ));

        mockMvc.perform(MockMvcRequestBuilders.put("/api/movies/" + m1.get_id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                        "_id": "65250133a87cf67dc7b57cdd",
                        "title": null,
                        "year": 2020,
                        "extract": "The Grudge is a 2020 American psychological supernatural horror film...",
                        "thumbnail": "https://upload.wikimedia.org/wikipedia/en/3/34/The_Grudge_2020_Poster.jpeg",
                        "isFavorite": true
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        "org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: title is marked non-null but is null"
                ));
    }

    @Test
    @DirtiesContext
    @WithMockUser
    void toggleIsFavorite_expectIsFavoriteHasChangedToTrue() throws Exception {
        boolean favoriteStatement = true;
        Movie m1 = movieRepo.save(new Movie(
                "65250133a87cf67dc7b57cdd",
                "The Grudge",
                2020,
                "The Grudge is a 2020 American psychological supernatural horror film...",
                "https://upload.wikimedia.org/wikipedia/en/3/34/The_Grudge_2020_Poster.jpeg",
                false
        ));

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/movies/" + m1.get_id() +"?favoriteStatement="+ favoriteStatement))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                        "_id": "65250133a87cf67dc7b57cdd",
                        "title": "The Grudge",
                        "year": 2020,
                        "extract": "The Grudge is a 2020 American psychological supernatural horror film...",
                        "thumbnail": "https://upload.wikimedia.org/wikipedia/en/3/34/The_Grudge_2020_Poster.jpeg",
                        "isFavorite": true
                        }
                        """
                ));
    }

    @Test
    @DirtiesContext
    @WithMockUser
    void toggleIsFavorite_expectIllegalArgumentException() throws Exception {
        boolean favoriteStatement = true;
        movieRepo.save(new Movie(
                "65250133a87cf67dc7b57cdd",
                "The Grudge",
                2020,
                "The Grudge is a 2020 American psychological supernatural horror film...",
                "https://upload.wikimedia.org/wikipedia/en/3/34/The_Grudge_2020_Poster.jpeg"
        ));

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/movies/" + "quatschId" +"?favoriteStatement="+ favoriteStatement))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        "IllegalArgument! Ein oder mehrere Übergabewerte sind falsch."
                ));
    }

    @Test
    @DirtiesContext
    @WithMockUser
    void deleteMovieById_expectDeleteMessage() throws Exception {

        String id = "65250133a87cf67dc7b57cdd";

        movieRepo.save(new Movie(
                "65250133a87cf67dc7b57cdd",
                "The Grudge",
                2020,
                "The Grudge is a 2020 American psychological supernatural horror film...",
                "https://upload.wikimedia.org/wikipedia/en/3/34/The_Grudge_2020_Poster.jpeg"
        ));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/movies/" + id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .string("Movie with id: 65250133a87cf67dc7b57cdd was deleted.")
                );
    }

    @Test
    @DirtiesContext
    @WithMockUser
    void deleteMovieById_expectIdNotFoundMessage() throws Exception {

        String id = "quatschId";

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/movies/" + id))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .string("die ID existiert nicht")
                );
    }
}