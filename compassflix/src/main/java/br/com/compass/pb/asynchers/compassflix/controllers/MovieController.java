package br.com.compass.pb.asynchers.compassflix.controllers;

import br.com.compass.pb.asynchers.compassflix.dto.request.MovieRequestDto;
import br.com.compass.pb.asynchers.compassflix.dto.response.MovieResponseDto;
import br.com.compass.pb.asynchers.compassflix.entities.Movie;
import br.com.compass.pb.asynchers.compassflix.services.MovieService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/compassflix/movies")
public class MovieController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MovieService service;

    @GetMapping
    public ResponseEntity<List<Movie>> findAll(UriComponentsBuilder builder) {
        var response = service.findAllMovies();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable String id) {

        return ResponseEntity.ok().body(mapper.map(service.findMovieById(id), Movie.class));

    }

    /*@GetMapping(params = "movieName")
    public ResponseEntity<List<MovieResponseDto>> searchByName(@RequestParam String movieName) {
        var response = service.searchByName(movieName);

        return ResponseEntity.ok().body(response);
    }*/

    @GetMapping(params = "search")
    public ResponseEntity<List<Movie>> findByName(@RequestParam("search") String name) {
        var response = service.findByName(name);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<MovieResponseDto> insert(@RequestBody @Valid MovieRequestDto movieRequestDto,
                                                   UriComponentsBuilder builder) {
        var response = service.postMovie(movieRequestDto);
        var uri = builder.path("/compassflix/movies/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDto> update(@PathVariable String id, @RequestBody MovieRequestDto movieRequestDto, UriComponentsBuilder builder) {
        MovieResponseDto updatedMovie = service.updateMovie(id, movieRequestDto);
        var uri = builder.path("/compassflix/movies/{id}").buildAndExpand(updatedMovie.id()).toUri();
        return ResponseEntity.ok(updatedMovie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}