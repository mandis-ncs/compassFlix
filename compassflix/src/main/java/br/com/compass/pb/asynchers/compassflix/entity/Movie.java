package br.com.compass.pb.asynchers.compassflix.entity;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Document
public class Movie {

    @Id
    private Integer id;

    private String name;

    private String description;

    private String genre;

    private Double duration;

    private Date releaseDate;

    private String pgRating;

    private LocalDateTime registrationDate;

    public Movie(String name, String description, String genre, Double duration, Date releaseDate, String pgRating, LocalDateTime registrationDate) {
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.pgRating = pgRating;
        this.registrationDate = registrationDate;
    }

}
