package br.com.compass.pb.asynchers.compassflix.controllers;

import br.com.compass.pb.asynchers.compassflix.entities.Movie;
import br.com.compass.pb.asynchers.compassflix.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieControllerTest {

    @Mock
    private ModelMapper mapper;
    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldBeFindAllAndReturnsListOfMovies() {
        Movie movie1 = new Movie();
        movie1.setId("64b1e14a36a86833234f6a42");
        movie1.setName("Bastardos inglorios");
        movie1.setDescription("Matando nazistas");
        movie1.setGenre("Acao");
        movie1.setDuration(120L);
        movie1.setReleaseDate(LocalDate.parse("2022-10-10"));
        movie1.setPgRating("pg-17");
        movie1.setRegistrationDate(Instant.parse("2023-07-14T19:13:25.465Z"));

        Movie movie2 = new Movie();
        movie2.setId("64b1e14a36a86833234f6a56");
        movie2.setName("Rei Leao");
        movie2.setDescription("Leos legais");
        movie2.setGenre("Animacao");
        movie2.setDuration(120L);
        movie2.setReleaseDate(LocalDate.parse("2022-10-10"));
        movie2.setPgRating("pg-12");
        movie2.setRegistrationDate(Instant.parse("2023-07-14T19:13:25.465Z"));

        Movie movie3 = new Movie();
        movie3.setId("64b1e14a36a86833234f6a98");
        movie3.setName("Batman");
        movie3.setDescription("Homem morcego");
        movie3.setGenre("Acao");
        movie3.setDuration(120L);
        movie3.setReleaseDate(LocalDate.parse("2022-10-10"));
        movie3.setPgRating("pg-5");
        movie3.setRegistrationDate(Instant.parse("2023-07-14T19:13:25.465Z"));

        // Arrange
        List<Movie> movies = Arrays.asList(
                movie1,
                movie2,
                movie3
        );
        when(movieService.findAllMovies()).thenReturn(movies);

        // Act
        ResponseEntity<List<Movie>> response = movieController.findAll();

        // Assert
        verify(movieService, times(1)).findAllMovies();
        verifyNoMoreInteractions(movieService);
        assertNotNull(response);
        assertSame(movies, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void shouldFindMovieByIdAndReturnMovie() {
        // Arrange
        String movieId = "64b1e14a36a86833234f6a42";
        Movie expectedMovie = new Movie();
        expectedMovie.setId(movieId);
        expectedMovie.setName("Bastardos inglorios");
        expectedMovie.setDescription("Matando nazistas");
        expectedMovie.setGenre("Acao");
        expectedMovie.setDuration(120L);
        expectedMovie.setReleaseDate(LocalDate.parse("2022-10-10"));
        expectedMovie.setPgRating("pg-17");
        expectedMovie.setRegistrationDate(Instant.parse("2023-07-14T19:13:25.465Z"));

        when(movieService.findMovieById(movieId)).thenReturn(expectedMovie);

        // Act
        ResponseEntity<Movie> response = movieController.findById(movieId);

        // Assert
        verify(movieService, times(1)).findMovieById(movieId);
        verifyNoMoreInteractions(movieService);
        assertNotNull(response);
        assertSame(expectedMovie, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }
}