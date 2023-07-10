package br.com.compass.pb.asynchers.compassflix.repository;

import br.com.compass.pb.asynchers.compassflix.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, Integer> {
}
