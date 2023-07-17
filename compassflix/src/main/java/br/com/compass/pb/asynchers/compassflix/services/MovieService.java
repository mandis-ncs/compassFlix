package br.com.compass.pb.asynchers.compassflix.services;

import br.com.compass.pb.asynchers.compassflix.dto.request.MovieRequestDto;
import br.com.compass.pb.asynchers.compassflix.dto.response.MovieResponseDto;
import br.com.compass.pb.asynchers.compassflix.entities.Movie;
import br.com.compass.pb.asynchers.compassflix.exceptions.ListIsEmptyException;
import br.com.compass.pb.asynchers.compassflix.exceptions.MovieAlreadyExistException;
import br.com.compass.pb.asynchers.compassflix.exceptions.MovieNotFoundException;
import br.com.compass.pb.asynchers.compassflix.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    @Autowired
    private final MovieRepository repository;

    public List<Movie> findAllMovies() {
        var response = repository.findAll();
        if (response.isEmpty()) {
            throw new ListIsEmptyException("No movies found!");
        }
        return response;

    }

    public Movie findMovieById(String id) {
        Optional<Movie> response = repository.findById(id);
        return response.orElseThrow(() -> new MovieNotFoundException("That movie doesn't exists!"));
    }

    public List<Movie> findByName(String name) {
        List<Movie> movies = repository.findByNameIgnoreCaseContaining(name);
        if (movies.isEmpty()) {
            throw new MovieNotFoundException("No movies found with the specified name: " + name);
        }
        return movies;
    }

    public MovieResponseDto postMovie(MovieRequestDto movieRequestDto) {
        log.info("### Calling post movie with name {} ###", movieRequestDto.name());
        if(repository.findAll().stream().anyMatch(movie -> movie.getName().equalsIgnoreCase(movieRequestDto.name()))) {
            throw new MovieAlreadyExistException("That movie already exists!");
        }
        var response = repository.save(new Movie(movieRequestDto));

        log.info("### Success at posting movie with name {} ###", movieRequestDto.name());
        return new MovieResponseDto(response);

    }

    public MovieResponseDto updateMovie(String id, MovieRequestDto obj) {
        log.info("### Searching movie by String Id {} ###", id);
        Optional<Movie> existingMovie = repository.findById(id);

        if (existingMovie.isPresent()) {
            log.info("### Updating movie ###");
            Movie movieToUpdate = existingMovie.get();
            movieToUpdate.setName(obj.name());
            movieToUpdate.setDescription(obj.description());
            movieToUpdate.setGenre(obj.genre());
            movieToUpdate.setDuration(obj.duration());
            movieToUpdate.setReleaseDate(obj.releaseDate());
            movieToUpdate.setPgRating(obj.pgRating());

            log.info("### Saving movie ###");
            Movie updatedMovie = repository.save(movieToUpdate);
            return new MovieResponseDto(updatedMovie);
        } else {
            throw new MovieNotFoundException("Movie not found!");
        }
    }

    public void delete(String id) {
        log.info("### Searching movie by String Id {} ###", id);
        var response = repository.findById(id);
        if (response.isEmpty()) {
            throw new MovieNotFoundException("That movie doesn't exists!");
        }
        log.info("### Deleted movie ###");
        repository.deleteById(id);
    }
}