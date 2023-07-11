package br.com.compass.pb.asynchers.compassflix.controller;

import br.com.compass.pb.asynchers.compassflix.entity.Movie;
import br.com.compass.pb.asynchers.compassflix.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compassflix")
public class MovieController {

    @Autowired
    private MovieService service;

//    @GetMapping("/movies")
//    public ResponseEntity<List<Movie>> FindAll() {
//    }
//
//    @RequestMapping(value="/{id}", method=RequestMethod.GET)
//    public ResponseEntity<Movie> findById(@PathVariable Integer id) {
//    }
//
//    @RequestMapping(method=RequestMethod.POST)
//    public ResponseEntity<Void> insert(@RequestBody Movie movie) {
//    }
//
//    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
//    public ResponseEntity<Void> delete(@PathVariable Integer id) {
//    }
//
//    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
//    public ResponseEntity<Void> update(@RequestBody Movie movie, @PathVariable Integer id) {
//    }
}
