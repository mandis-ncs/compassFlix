package br.com.compass.pb.asynchers.compassflix.dto.response;

import br.com.compass.pb.asynchers.compassflix.entities.Movie;

import java.time.Instant;

public record MovieResponseDto(
        String id,
        String name,
        String description,
        String genre,
        Double duration,
        String releaseDate,
        String pgRating,
        Instant registrationDate
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
