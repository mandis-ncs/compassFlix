package br.com.compass.pb.asynchers.compassflix.dto.response;

import br.com.compass.pb.asynchers.compassflix.entities.Movie;

import java.time.LocalDateTime;
import java.util.Date;

public record MovieResponseDto(
        Long id,
        String name,
        String description,
        String genre,
        Double duration,
        Date releaseDate,
        String pgRating,
        LocalDateTime registrationDate
) {

    public MovieResponseDto(Movie response) {
        this(
                response.getId(),
                response.getName(),
                response.getDescription(),
                response.getGenre(),
                response.getDuration(),
                response.getReleaseDate(),
                response.getPgRating(),
                response.getRegistrationDate()
        );
    }
}
