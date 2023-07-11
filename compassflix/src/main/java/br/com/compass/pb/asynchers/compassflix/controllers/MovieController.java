package br.com.compass.pb.asynchers.compassflix.controllers;

import br.com.compass.pb.asynchers.compassflix.dto.request.MovieRequestDto;
import br.com.compass.pb.asynchers.compassflix.dto.response.MovieResponseDto;
import br.com.compass.pb.asynchers.compassflix.entities.Movie;
import br.com.compass.pb.asynchers.compassflix.services.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/compassflix")
public class MovieController {

    @Autowired
    private MovieService service;

    /*@GetMapping
    public ResponseEntity<List<Movie>> FindAll() {
    }

    @GetMapping
    public ResponseEntity<Movie> findById(@PathVariable Integer id) {
    } */

    @PostMapping
    public ResponseEntity<MovieResponseDto> insert(@RequestBody @Valid MovieRequestDto movieRequestDto, UriComponentsBuilder builder) {
        var response = service.postMovie(movieRequestDto);
        var uri = builder.path("/compassflix/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    /*@DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody Movie movie, @PathVariable Integer id) {
    }*/
}
