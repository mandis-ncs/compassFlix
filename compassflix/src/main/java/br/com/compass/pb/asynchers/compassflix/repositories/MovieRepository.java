package br.com.compass.pb.asynchers.compassflix.repositories;

import br.com.compass.pb.asynchers.compassflix.entities.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<Movie, Integer> {
}
