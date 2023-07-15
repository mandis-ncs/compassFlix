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

    /*public List<MovieResponseDto> searchByName(String name) {

        return repository.findAll().stream().filter(movie -> movie.getName().contains(name)).
                map(MovieResponseDto::new).toList();

    }*/

    public List<Movie> findByName(String name) {

        return repository.findByNameIgnoreCaseContaining(name);
    }

    public MovieResponseDto postMovie(MovieRequestDto movieRequestDto) {
        log.info("### Calling post movie with name {} ###", movieRequestDto.name());
        if(repository.findAll().stream().anyMatch(movie -> movie.getName().equalsIgnoreCase(movieRequestDto.name()))) {
            throw new MovieAlreadyExistException("That movie already exists!");
        }
        var response = repository.save(new Movie(movieRequestDto));

        log.info("### Success at posting movie with name {} ###", movieRequestDto.name());
        return new MovieResponseDto(response);

        // MovieAlreadyExistException -> verify if product already exists
    }



    public MovieResponseDto updateMovie(String id, MovieRequestDto obj) {
        log.info("### Searching movie by String Id {} ###", id);
        Movie existingMovie = findMovieById(id);

        if (existingMovie == null) {
            throw new MovieNotFoundException("Movie not found");
        }

        log.info("### Updating movie ###");
        existingMovie.setName(obj.name());
        existingMovie.setDescription(obj.description());
        existingMovie.setGenre(obj.genre());
        existingMovie.setDuration(obj.duration());
        existingMovie.setReleaseDate(obj.releaseDate());
        existingMovie.setPgRating(obj.pgRating());

        log.info("### Saving movie ###");
        Movie updatedMovie = repository.save(existingMovie);
        return new MovieResponseDto(updatedMovie);
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