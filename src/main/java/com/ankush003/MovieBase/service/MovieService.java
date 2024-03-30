package com.ankush003.MovieBase.service;

import com.ankush003.MovieBase.model.entities.MovieEntity;

import java.util.List;

public interface MovieService {
    MovieEntity addMovie(MovieEntity movieEntity);
    List<MovieEntity> getAllMovies();
}
