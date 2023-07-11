package br.com.compass.pb.asynchers.compassflix.service;

import br.com.compass.pb.asynchers.compassflix.entity.Movie;
import br.com.compass.pb.asynchers.compassflix.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movie;

//    public List<Movie> findAll() {
//    }
//
//    public Movie findById(Integer id) {
//    }
//
//    public Movie insert(Movie obj) {
//    }
//
//    public void delete(Integer id) {
//    }
//
//    public Movie update(Movie obj) {
//    }



}
