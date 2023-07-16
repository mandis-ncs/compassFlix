package br.com.compass.pb.asynchers.compassflix.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

// validation annotations go here

public record MovieRequestDto(
        @NotNull
        String name,
        @NotNull
        String description,
        @NotNull
        String genre,
        @NotNull
        Long duration,
        @NotNull
        LocalDate releaseDate,
        @NotNull
        String pgRating
) {
}
