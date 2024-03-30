package com.ankush003.MovieBase.service;

import com.ankush003.MovieBase.model.entities.MovieEntity;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    MovieEntity addMovie(MovieEntity movieEntity);
    List<MovieEntity> getAllMovies();
    Optional<MovieEntity> getMovieById(Long id);
}
