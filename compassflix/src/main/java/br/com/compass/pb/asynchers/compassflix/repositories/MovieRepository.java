package br.com.compass.pb.asynchers.compassflix.repositories;

import br.com.compass.pb.asynchers.compassflix.entities.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    //Optional<Movie> findByStringId(String id);

}
