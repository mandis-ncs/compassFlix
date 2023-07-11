package br.com.compass.pb.asynchers.compassflix.dto.request;

import jakarta.validation.constraints.NotNull;

// validation annotations go here

public record MovieRequestDto(
        @NotNull
        String name,
        @NotNull
        String description,
        @NotNull
        String genre,
        @NotNull
        Double duration,
        @NotNull
        String releaseDate,
        @NotNull
        String pgRating
) {
}
