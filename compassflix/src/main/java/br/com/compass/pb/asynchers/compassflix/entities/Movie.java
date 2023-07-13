package br.com.compass.pb.asynchers.compassflix.entities;

import br.com.compass.pb.asynchers.compassflix.dto.request.MovieRequestDto;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Document
public class Movie {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    private String description;

    private String genre;

    private Long duration;

    private LocalDate releaseDate;

    private String pgRating;

    private Instant registrationDate;

    public Movie(MovieRequestDto movieRequestDto) {
        this.name = movieRequestDto.name().toLowerCase();
        this.description = movieRequestDto.description();
        this.genre = movieRequestDto.genre();
        this.duration = movieRequestDto.duration();
        this.releaseDate = movieRequestDto.releaseDate();
        this.pgRating = movieRequestDto.pgRating();
        this.registrationDate = Instant.now(Clock.systemDefaultZone());
    }

}
