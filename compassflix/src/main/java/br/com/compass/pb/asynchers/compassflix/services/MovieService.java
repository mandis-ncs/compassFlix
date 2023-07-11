package br.com.compass.pb.asynchers.compassflix.services;

import br.com.compass.pb.asynchers.compassflix.dto.request.MovieRequestDto;
import br.com.compass.pb.asynchers.compassflix.dto.response.MovieResponseDto;
import br.com.compass.pb.asynchers.compassflix.entities.Movie;
import br.com.compass.pb.asynchers.compassflix.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    private final MovieRepository repo;

    /*public List<Movie> findAll() {
    }

    public Movie findById(Integer id) {
    }*/

    public MovieResponseDto postMovie(MovieRequestDto movieRequestDto) {
        log.info("### chamando post movie com nome {} ###", movieRequestDto.name());
        var response = repo.save(new Movie(movieRequestDto));
        log.info("### post movie com nome {} foi sucesso ###", movieRequestDto.name());
        return new MovieResponseDto(response);
    }

    /*public void delete(Integer id) {
    }

    public Movie update(Movie obj) {
    }*/



}
