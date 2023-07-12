package br.com.compass.pb.asynchers.compassflix.controllers;

import br.com.compass.pb.asynchers.compassflix.dto.request.MovieRequestDto;
import br.com.compass.pb.asynchers.compassflix.dto.response.MovieResponseDto;
import br.com.compass.pb.asynchers.compassflix.entities.Movie;
import br.com.compass.pb.asynchers.compassflix.services.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compassflix/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @GetMapping
    public ResponseEntity<List<MovieResponseDto>> findAll(UriComponentsBuilder builder) {
        var response = service.findAllMovies();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Movie>> findById(@PathVariable String id) {

        var response = service.findMovieById(id);
        return ResponseEntity.ok(response);

    }

    @PostMapping
    public ResponseEntity<MovieResponseDto> insert(@RequestBody @Valid MovieRequestDto movieRequestDto,
                                                   UriComponentsBuilder builder) {
        var response = service.postMovie(movieRequestDto);
        var uri = builder.path("/compassflix/movies/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDto> update(@RequestBody @Valid MovieRequestDto movieRequestDto,
                                                   @PathVariable String id, UriComponentsBuilder builder) {
        var response = service.putMovie(movieRequestDto, id);
        var uri = builder.path("/compassflix/movies/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.ok().body(response);
    }


    /*@DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
    }*/


}
