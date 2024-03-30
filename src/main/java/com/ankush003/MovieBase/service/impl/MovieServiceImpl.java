package com.ankush003.MovieBase.service.impl;

import com.ankush003.MovieBase.model.entities.MovieEntity;
import com.ankush003.MovieBase.repository.MovieRepository;
import com.ankush003.MovieBase.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieEntity addMovie(MovieEntity movieEntity) {
        return movieRepository.save(movieEntity);
    }

    @Override
    public List<MovieEntity> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<MovieEntity> getMovieById(Long id) {
        return movieRepository.findById(id);
    }
}
