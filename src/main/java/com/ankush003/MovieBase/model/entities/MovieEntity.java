package com.ankush003.MovieBase.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "movies")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "release_date")
//    @JsonProperty("release_date")
    private LocalDate releaseDate;

    @Column(name = "genre")
    private String genre;

    @Column(name = "director")
    private String director;

    @Column(name = "total_reviews")
    @ColumnDefault("0")
    private Integer totalReviews;

    @Column(name = "accumulated_rating")
    @ColumnDefault("0.0")
    private Double accumulatedRating;

    @Column(name = "average_rating")
    @ColumnDefault("0.0")
    private Double averageRating;

    // relations
    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "movie-review")
    private List<ReviewEntity> reviews;
}
