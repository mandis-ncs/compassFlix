package br.com.compass.pb.asynchers.compassflix.entities;

import br.com.compass.pb.asynchers.compassflix.dto.request.MovieRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String genre;

    private Double duration;

    private Date releaseDate;

    private String pgRating;

    private LocalDateTime registrationDate;

    public Movie(MovieRequestDto movieRequestDto) {
        this.name = movieRequestDto.name();
        this.description = movieRequestDto.description();
        this.genre = movieRequestDto.genre();
        this.duration = movieRequestDto.duration();
        this.releaseDate = movieRequestDto.releaseDate();
        this.pgRating = movieRequestDto.pgRating();
        this.registrationDate = movieRequestDto.registrationDate();
    }

}
