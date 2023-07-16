package br.com.compass.pb.asynchers.compassflix;

import br.com.compass.pb.asynchers.compassflix.entities.Movie;
import br.com.compass.pb.asynchers.compassflix.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabasePopulator implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public DatabasePopulator(MongoTemplate mongoTemplate, MovieRepository movieRepository) {
        this.mongoTemplate = mongoTemplate;
        this.movieRepository = movieRepository;
    }

    private List<Movie> createMovies() {
        Movie movie1 = new Movie();
        movie1.setName("Inglorious bastards");
        movie1.setDescription("Killing nazis");
        movie1.setGenre("Action");
        movie1.setDuration(120L);
        movie1.setReleaseDate(LocalDate.of(2009, 10, 9));
        movie1.setPgRating("pg-18");
        movie1.setRegistrationDate(Instant.now());

        Movie movie2 = new Movie();
        movie2.setName("The lord of the rings: the fellowship of the ring");
        movie2.setDescription("Beginning of the journey");
        movie2.setGenre("Fantasy");
        movie2.setDuration(180L);
        movie2.setReleaseDate(LocalDate.of(2002, 1, 1));
        movie2.setPgRating("pg-16");
        movie2.setRegistrationDate(Instant.now());

        Movie movie3 = new Movie();
        movie3.setName("The lord of the rings: the two towers");
        movie3.setDescription("The middle of the journey");
        movie3.setGenre("Fantasy");
        movie3.setDuration(180L);
        movie3.setReleaseDate(LocalDate.of(2003, 1, 1));
        movie3.setPgRating("pg-16");
        movie3.setRegistrationDate(Instant.now());

        Movie movie4 = new Movie();
        movie4.setName("The lord of the rings: return of the king");
        movie4.setDescription("The end of the journey");
        movie4.setGenre("Fantasy");
        movie4.setDuration(180L);
        movie4.setReleaseDate(LocalDate.of(2004, 2, 1));
        movie4.setPgRating("pg-16");
        movie4.setRegistrationDate(Instant.now());

        Movie movie5 = new Movie();
        movie5.setName("The hobbit: there and back again");
        movie5.setDescription("Who don't like hobbits?");
        movie5.setGenre("Fantasy");
        movie5.setDuration(120L);
        movie5.setReleaseDate(LocalDate.of(2008, 7, 20));
        movie5.setPgRating("PG-13");
        movie5.setRegistrationDate(Instant.now());

        Movie movie6 = new Movie();
        movie6.setName("Peter pan");
        movie6.setDescription("Once a kid, always a kid");
        movie6.setGenre("Adventure");
        movie6.setDuration(90L);
        movie6.setReleaseDate(LocalDate.of(1980, 3, 11));
        movie6.setPgRating("pg-3");

        Movie movie7 = new Movie();
        movie7.setName("The return of those who never went");
        movie7.setDescription("They didn't leave");
        movie7.setGenre("Horror");
        movie7.setDuration(87L);
        movie7.setReleaseDate(LocalDate.of(1950, 4, 3));
        movie7.setPgRating("pg-18");
        movie7.setRegistrationDate(Instant.now());


        return Arrays.asList(movie1, movie2, movie3, movie4, movie5, movie6, movie7);
    }

    private void deleteAndCreateDatabase() {
        mongoTemplate.getDb().drop();
        mongoTemplate.getDb().createCollection("movies");
    }

    @Override
    public void run(String... args) {
        deleteAndCreateDatabase();

        if (movieRepository.count() == 0) {
            List<Movie> movies = createMovies();
            movieRepository.insert(movies);
            System.out.println("Database populated with movies.");
        }
    }
}