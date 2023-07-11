package br.com.compass.pb.asynchers.compassflix.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

// validation annotations go here

public record MovieRequestDto(
        @NotNull
        @NotBlank
        String name,

        @NotNull
        @NotBlank
        String description,

        @NotNull
        @NotBlank
        String genre,
        @NotNull
        @NotBlank
        Double duration,
        @NotNull
        @NotBlank
        Date releaseDate,
        @NotNull
        @NotBlank
        String pgRating,
        @NotNull
        @NotBlank
        LocalDateTime registrationDate
) {
}
