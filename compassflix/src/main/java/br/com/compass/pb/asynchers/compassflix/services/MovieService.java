package br.com.compass.pb.asynchers.compassflix.services;

import br.com.compass.pb.asynchers.compassflix.dto.request.MovieRequestDto;
import br.com.compass.pb.asynchers.compassflix.dto.response.MovieResponseDto;
import br.com.compass.pb.asynchers.compassflix.entities.Movie;
import br.com.compass.pb.asynchers.compassflix.exceptions.ListIsEmptyException;
import br.com.compass.pb.asynchers.compassflix.exceptions.MovieNotFoundException;
import br.com.compass.pb.asynchers.compassflix.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    @Autowired
    private final MovieRepository repository;

    public List<MovieResponseDto> findAllMovies() {
        var response = repository.findAll();
        var movies = new ArrayList<MovieResponseDto>();
        if (response.isEmpty()) {
            throw new ListIsEmptyException("No movies found!");
        }
        response.forEach(movie -> movies.add(new MovieResponseDto(movie))); //lambda
        return movies;

    }

    public Optional<Movie> findMovieById(String id) {
        var response = repository.findById(id);
        if (response.isEmpty()) {
            throw new MovieNotFoundException("That movie doesn't exists!");
        }
        return response;
    }

    public MovieResponseDto postMovie(MovieRequestDto movieRequestDto) {
        log.info("### Calling post movie with name {} ###", movieRequestDto.name());
        var response = repository.save(new Movie(movieRequestDto));

        log.info("### Success at posting movie with name {} ###", movieRequestDto.name());
        return new MovieResponseDto(response);

        // MovieAlreadyExistException -> verify if product already exists
    }



    public MovieResponseDto putMovie(MovieRequestDto movieRequestDto, String id) {

        log.info("### Searching movie by String Id {} ###", id);
        Optional<Movie> optionalMovie = repository.findById(id);
        if (optionalMovie.isEmpty()) {
            throw new MovieNotFoundException("Movie not found");
        }

        log.info("### Updating movie ###");
        Movie existingMovie = optionalMovie.get();
        existingMovie.setName(movieRequestDto.name());
        existingMovie.setDescription(movieRequestDto.description());
        existingMovie.setGenre(movieRequestDto.genre());
        existingMovie.setDuration(movieRequestDto.duration());
        existingMovie.setReleaseDate(movieRequestDto.releaseDate());
        existingMovie.setPgRating(movieRequestDto.pgRating());

        log.info("### Saving movie ###");
        Movie updatedMovie =repository.save(existingMovie);
        return new MovieResponseDto(updatedMovie);
    }

    /*public void delete(Integer id) {
    }*/





}
