package br.com.compass.pb.asynchers.compassflix;

import br.com.compass.pb.asynchers.compassflix.entities.Movie;
import br.com.compass.pb.asynchers.compassflix.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabasePopulator implements CommandLineRunner {

    private final MovieRepository movieRepository;

    @Autowired
    public DatabasePopulator(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    private List<Movie> createMovies() {
        Movie movie1 = new Movie();
        movie1.setName("Bastardos inglorios");
        movie1.setDescription("Matando nazistas");
        movie1.setGenre("Ação");
        movie1.setDuration(120L);
        movie1.setReleaseDate(LocalDate.of(2009, 10, 9));
        movie1.setPgRating("pg-18");
        movie1.setRegistrationDate(Instant.now());

        Movie movie2 = new Movie();
        movie2.setName("O Senhor dos Aneis: A Sociedade do Anel");
        movie2.setDescription("Começando a jornada do anel");
        movie2.setGenre("Fantasia");
        movie2.setDuration(180L);
        movie2.setReleaseDate(LocalDate.of(2002, 1, 1));
        movie2.setPgRating("pg-16");
        movie2.setRegistrationDate(Instant.now());

        Movie movie3 = new Movie();
        movie3.setName("O Senhor dos Aneis: As duas torres");
        movie3.setDescription("O meio da jornada do anel");
        movie3.setGenre("Fantasia");
        movie3.setDuration(180L);
        movie3.setReleaseDate(LocalDate.of(2003, 1, 1));
        movie3.setPgRating("pg-16");
        movie3.setRegistrationDate(Instant.now());

        Movie movie4 = new Movie();
        movie4.setName("O Senhor dos Aneis: o retorno do rei");
        movie4.setDescription("O fim da jornada do anel");
        movie4.setGenre("Fantasia");
        movie4.setDuration(180L);
        movie4.setReleaseDate(LocalDate.of(2004, 2, 1));
        movie4.setPgRating("pg-16");
        movie4.setRegistrationDate(Instant.now());

        Movie movie5 = new Movie();
        movie5.setName("As Cronicas de Narnia: O Leao, o Guarda-Roupa e a Feiticeira");
        movie5.setDescription("Crianças num mundo fantastico");
        movie5.setGenre("Fantasia");
        movie5.setDuration(120L);
        movie5.setReleaseDate(LocalDate.of(2008, 7, 20));
        movie5.setPgRating("PG-13");
        movie5.setRegistrationDate(Instant.now());

        Movie movie6 = new Movie();
        movie6.setName("Peter pan");
        movie6.setDescription("A criança que nunca cresce");
        movie6.setGenre("Aventura");
        movie6.setDuration(90L);
        movie6.setReleaseDate(LocalDate.of(1980, 3, 11));
        movie6.setPgRating("pg-3");

        Movie movie7 = new Movie();
        movie7.setName("A volta dos que não foram");
        movie7.setDescription("Eles não foram");
        movie7.setGenre("Terror");
        movie7.setDuration(87L);
        movie7.setReleaseDate(LocalDate.of(1950, 4, 3));
        movie7.setPgRating("pg-18");
        movie7.setRegistrationDate(Instant.now());


        return Arrays.asList(movie1, movie2, movie3, movie4, movie5, movie6, movie7);
    }

    @Override
    public void run(String... args) {
        if (movieRepository.count() == 0) {
            List<Movie> movies = createMovies();
            movieRepository.insert(movies);
            System.out.println("Database populated with movies.");
        }
    }
}