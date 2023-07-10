package br.com.compass.pb.asynchers.compassflix.dto.request;

import java.time.LocalDateTime;
import java.util.Date;

// validation annotations go here

public record MovieRequestDto(
        String name,
        String description,
        String genre,
        Double duration,
        Date releaseDate,
        String pgRating,
        LocalDateTime registrationDate
) {
}
