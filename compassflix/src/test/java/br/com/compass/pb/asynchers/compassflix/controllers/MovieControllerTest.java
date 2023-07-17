package br.com.compass.pb.asynchers.compassflix.controllers;

import br.com.compass.pb.asynchers.compassflix.dto.request.MovieRequestDto;
import br.com.compass.pb.asynchers.compassflix.dto.response.MovieResponseDto;
import br.com.compass.pb.asynchers.compassflix.entities.Movie;
import br.com.compass.pb.asynchers.compassflix.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieControllerTest {

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
        movie1.setName("Avengers");
        movie1.setDescription("Heroes fighting");
        movie1.setGenre("Action");
        movie1.setDuration(120L);
        movie1.setReleaseDate(LocalDate.parse("2022-10-10"));
        movie1.setPgRating("pg-17");
        movie1.setRegistrationDate(Instant.parse("2023-07-14T19:13:25.465Z"));

        Movie movie2 = new Movie();
        movie2.setId("64b1e14a36a86833234f6a56");
        movie2.setName("Lion King");
        movie2.setDescription("Cool lions");
        movie2.setGenre("Animation");
        movie2.setDuration(120L);
        movie2.setReleaseDate(LocalDate.parse("2022-10-10"));
        movie2.setPgRating("pg-12");
        movie2.setRegistrationDate(Instant.parse("2023-07-14T19:13:25.465Z"));

        Movie movie3 = new Movie();
        movie3.setId("64b1e14a36a86833234f6a98");
        movie3.setName("Batman");
        movie3.setDescription("Dark hero");
        movie3.setGenre("Action");
        movie3.setDuration(120L);
        movie3.setReleaseDate(LocalDate.parse("2022-10-10"));
        movie3.setPgRating("pg-14");
        movie3.setRegistrationDate(Instant.parse("2023-07-14T19:13:25.465Z"));

        List<Movie> movies = Arrays.asList(
                movie1,
                movie2,
                movie3
        );
        when(movieService.findAllMovies()).thenReturn(movies);

        ResponseEntity<List<Movie>> response = movieController.findAll();

        verify(movieService, times(1)).findAllMovies();
        verifyNoMoreInteractions(movieService);
        assertNotNull(response);
        assertSame(movies, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void shouldFindMovieByIdAndReturnMovie() {
        String movieId = "64b1e14a36a86833234f6a42";
        Movie expectedMovie = new Movie();
        expectedMovie.setId(movieId);
        expectedMovie.setName("Avengers");
        expectedMovie.setDescription("Heroes fighting");
        expectedMovie.setGenre("Action");
        expectedMovie.setDuration(120L);
        expectedMovie.setReleaseDate(LocalDate.parse("2022-10-10"));
        expectedMovie.setPgRating("pg-17");
        expectedMovie.setRegistrationDate(Instant.parse("2023-07-14T19:13:25.465Z"));

        String expectedToString = "Movie(" +
                "id=" + movieId +
                ", name=Avengers" +
                ", description=Heroes fighting" +
                ", genre=Action" +
                ", duration=120" +
                ", releaseDate=2022-10-10" +
                ", pgRating=pg-17" +
                ", registrationDate=2023-07-14T19:13:25.465Z" +
                ")";

        when(movieService.findMovieById(movieId)).thenReturn(expectedMovie);

        ResponseEntity<Movie> response = movieController.findById(movieId);

        verify(movieService, times(1)).findMovieById(movieId);
        verifyNoMoreInteractions(movieService);
        assertNotNull(response);
        assertSame(expectedMovie, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedToString, expectedMovie.toString());

    }

    @Test
    void shouldBeAbleToFindMovieByName() {
        Movie movie1 = new Movie();
        movie1.setId("64b1e14a36a86833234f6a42");
        movie1.setName("Avengers");
        movie1.setDescription("Heroes fighting");
        movie1.setGenre("Action");
        movie1.setDuration(120L);
        movie1.setReleaseDate(LocalDate.parse("2022-10-10"));
        movie1.setPgRating("pg-17");
        movie1.setRegistrationDate(Instant.parse("2023-07-14T19:13:25.465Z"));

        Movie movie2 = new Movie();
        movie2.setId("64b1e14a36a86833234f6a56");
        movie2.setName("Lion King");
        movie2.setDescription("Cool lions");
        movie2.setGenre("Animation");
        movie2.setDuration(120L);
        movie2.setReleaseDate(LocalDate.parse("2022-10-10"));
        movie2.setPgRating("pg-12");
        movie2.setRegistrationDate(Instant.parse("2023-07-14T19:13:25.465Z"));

        Movie movie3 = new Movie();
        movie3.setId("64b1e14a36a86833234f6a98");
        movie3.setName("Batman");
        movie3.setDescription("Dark hero");
        movie3.setGenre("Action");
        movie3.setDuration(120L);
        movie3.setReleaseDate(LocalDate.parse("2022-10-10"));
        movie3.setPgRating("pg-14");
        movie3.setRegistrationDate(Instant.parse("2023-07-14T19:13:25.465Z"));

        List<Movie> movies = Arrays.asList(
                movie1,
                movie2,
                movie3
        );
        when(movieService.findByName("Avengers")).thenReturn(movies);

        ResponseEntity<List<Movie>> response = movieController.findByName("Avengers");

        verify(movieService, times(1)).findByName("Avengers");
        verifyNoMoreInteractions(movieService);
        assertNotNull(response);
        assertSame(movies, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void shouldBeAbleToPostAMovie() {
        MovieRequestDto movieRequestDto = new MovieRequestDto(
                "Avengers", "Heroes fighting", "Action",
                120L, LocalDate.parse("2022-10-10"), "pg-17");

        Movie movie = new Movie(movieRequestDto);
        movie.setId("64b1e14a36a86833234f6a42");
        movie.setRegistrationDate(Instant.parse("2023-07-14T19:13:25.465Z"));

        String id = movie.getId();

        MovieResponseDto movieResponseDto = new MovieResponseDto(movie);

        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/");

        when(movieService.postMovie(movieRequestDto)).thenReturn(movieResponseDto);

        ResponseEntity<MovieResponseDto> createdMovie = movieController.insert(movieRequestDto, builder);

        verify(movieService, times(1)).postMovie(movieRequestDto);
        verifyNoMoreInteractions(movieService);
        assertNotNull(createdMovie);
        assertSame(movieResponseDto, createdMovie.getBody());
        assertEquals(201, createdMovie.getStatusCodeValue());

        String expectedPath = "/compassflix/movies/" + id;
        URI expectedUri = UriComponentsBuilder.fromPath(expectedPath).build().toUri();

        URI locationUri = createdMovie.getHeaders().getLocation();
        assertNotNull(locationUri);
        String returnedUri = locationUri.toString();
        assertEquals(expectedUri.toString(), returnedUri);

  }

    @Test
    void shouldBeAbleToUpdateAMovie() {
        MovieRequestDto movieRequestDto = new MovieRequestDto(
                "Avengers",
                "Heroes fighting",
                "Action",
                120L,
                LocalDate.parse("2022-10-10"),
                "pg-17");


        Movie movie = new Movie();
        movie.setId("64b1e14a36a86833234f6a42");
        movie.setName("Avengers");
        movie.setDescription("Heroes fighting");
        movie.setGenre("Action");
        movie.setDuration(120L);
        movie.setReleaseDate(LocalDate.parse("2022-10-10"));
        movie.setPgRating("pg-17");
        movie.setRegistrationDate(Instant.parse("2023-07-14T19:13:25.465Z"));


        MovieResponseDto movieResponseDto = new MovieResponseDto(movie);
        String id = "64b1e14a36a86833234f6a42";
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("/");

        when(movieService.updateMovie(id, movieRequestDto)).thenReturn(movieResponseDto);

        String expectedPath = "/compassflix/movies/" + id;
        URI expectedUri = UriComponentsBuilder.fromPath(expectedPath).build().toUri();

        ResponseEntity<MovieResponseDto> updatedMovie = movieController.update(id, movieRequestDto, builder);

        verify(movieService, times(1)).updateMovie(id, movieRequestDto);
        verifyNoMoreInteractions(movieService);
        assertNotNull(updatedMovie);
        assertSame(movieResponseDto, updatedMovie.getBody());
        assertEquals(200, updatedMovie.getStatusCodeValue());

        URI locationUri = updatedMovie.getHeaders().getLocation();
        assertNotNull(locationUri);
        String returnedUri = locationUri.toString();
        assertEquals(expectedUri.toString(), returnedUri);

    }

    @Test
    void shouldBeAbleToDeleteAMovie() {

        String id = "64b1e14a36a86833234f6a42";

        doNothing().when(movieService).delete(id);

        ResponseEntity<Void> deleteResponse = movieController.delete(id);

        verify(movieService, times(1)).delete(id);
        assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());
        assertNull(deleteResponse.getBody());

    }
}