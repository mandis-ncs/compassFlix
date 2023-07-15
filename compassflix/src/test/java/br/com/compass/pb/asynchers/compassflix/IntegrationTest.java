package br.com.compass.pb.asynchers.compassflix;

import br.com.compass.pb.asynchers.compassflix.controllers.MovieController;
import br.com.compass.pb.asynchers.compassflix.entities.Movie;
import br.com.compass.pb.asynchers.compassflix.exceptions.ListIsEmptyException;
import br.com.compass.pb.asynchers.compassflix.exceptions.MovieNotFoundException;
import br.com.compass.pb.asynchers.compassflix.services.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieController controller;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MovieService service;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void shouldBeAbleToFindAllMovies() throws Exception {

        Movie movie = new Movie();
        movie.setId("64b19e553e1e2a527bd18ff6");
        movie.setName("Bastardos inglorios");
        movie.setDescription("Matando nazistas");
        movie.setGenre("Acao");
        movie.setDuration(120L);
        movie.setReleaseDate(LocalDate.parse("2022-10-10"));
        movie.setPgRating("pg-17");
        movie.setRegistrationDate(Instant.parse("2023-07-14T19:13:25.465Z"));

//        String responseBody = objectMapper.writeValueAsString(movie);

        when(service.findAllMovies()).thenReturn(List.of(movie));

        String responseExpected = "[{\"id\":\"64b19e553e1e2a527bd18ff6\"," +
                "\"name\":\"Bastardos inglorios\"," +
                "\"description\":\"Matando nazistas\"," +
                "\"genre\":\"Acao\"," +
                "\"duration\":120," +
                "\"releaseDate\":[2022,10,10]," +
                "\"pgRating\":\"pg-17\"," +
                "\"registrationDate\":1689362005.465000000}]";

        // Realiza a requisição GET para o endpoint /compassflix/movies
        mockMvc.perform(get("/compassflix/movies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(responseExpected))
                .andExpect(status().isOk())
                .andReturn();

        verify(service, times(1)).findAllMovies();
    }

    @Test
    void shouldReturnAnListIsEmptyException() throws Exception {

        when(service.findAllMovies()).thenThrow(new ListIsEmptyException("No movies found!"));

        mockMvc.perform(get("/compassflix/movies"))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ListIsEmptyException))
                .andExpect(result -> assertEquals("No movies found!", result.getResolvedException().getMessage()))
                .andReturn();

        verify(service, times(1)).findAllMovies();
    }

    @Test
    void shouldBeAbleToFindMovieById() throws Exception {

        Movie movie = new Movie();
        movie.setId("64b19e553e1e2a527bd18ff6");
        movie.setName("Bastardos inglorios");
        movie.setDescription("Matando nazistas");
        movie.setGenre("Acao");
        movie.setDuration(120L);
        movie.setReleaseDate(LocalDate.parse("2022-10-10"));
        movie.setPgRating("pg-17");
        movie.setRegistrationDate(Instant.parse("2023-07-14T19:13:25.465Z"));

//        String responseBody = objectMapper.writeValueAsString(movie);

        when(service.findMovieById("64b19e553e1e2a527bd18ff6")).thenReturn((movie));

        String responseExpected = "{\"id\":\"64b19e553e1e2a527bd18ff6\"," +
                "\"name\":\"Bastardos inglorios\"," +
                "\"description\":\"Matando nazistas\"," +
                "\"genre\":\"Acao\"," +
                "\"duration\":120," +
                "\"releaseDate\":[2022,10,10]," +
                "\"pgRating\":\"pg-17\"," +
                "\"registrationDate\":1689362005.465000000}";

        // Realiza a requisição GET para o endpoint /compassflix/movies
        mockMvc.perform(get("/compassflix/movies/64b19e553e1e2a527bd18ff6")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(responseExpected))
                .andExpect(status().isOk())
                .andReturn();

        verify(service, times(1)).findMovieById("64b19e553e1e2a527bd18ff6");
    }

    @Test
    void shouldReturnAnMovieNotFoundException() throws Exception {

        when(service.findMovieById("id-not-found")).thenThrow(new MovieNotFoundException("That movie doesn't exist!"));

        mockMvc.perform(get("/compassflix/movies/id-not-found"))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MovieNotFoundException))
                .andExpect(result -> assertEquals("That movie doesn't exist!", result.getResolvedException().getMessage()))
                .andReturn();

        verify(service, times(1)).findMovieById("id-not-found");
    }

}
