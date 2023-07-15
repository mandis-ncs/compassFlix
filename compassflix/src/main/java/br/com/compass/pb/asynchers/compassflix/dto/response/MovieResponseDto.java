package br.com.compass.pb.asynchers.compassflix.dto.response;

import br.com.compass.pb.asynchers.compassflix.entities.Movie;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public record MovieResponseDto(
        String id,
        String name,
        String description,
        String genre,
        Long duration,
        LocalDate releaseDate,
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
