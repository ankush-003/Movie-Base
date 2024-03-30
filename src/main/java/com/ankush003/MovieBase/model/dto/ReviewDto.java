package com.ankush003.MovieBase.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    private Long id;
    private String review;
    private Double rating;
    private Instant createdAt;
    private MovieDto movie;
    private UserDto user;
}