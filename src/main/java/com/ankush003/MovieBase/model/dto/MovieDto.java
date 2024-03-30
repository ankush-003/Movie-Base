package com.ankush003.MovieBase.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {
    private Long id;
    private String title;
    private LocalDate releaseDate;
    private String genre;
    private String director;
    private Double averageRating;
    private List<ReviewDto> reviews;
}
