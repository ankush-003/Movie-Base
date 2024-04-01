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
        movieEntity.setAverageRating(0.0);
        movieEntity.setAccumulatedRating(0.0);
        movieEntity.setTotalReviews(0);
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

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public MovieEntity updateMovie(MovieEntity movieEntity) {
        return movieRepository.save(movieEntity);
    }

    @Override
    public List<MovieEntity> findMoviesByGenre(String genre) {
        return movieRepository.findMoviesByGenre(genre);
    }

    @Override
    public List<MovieEntity> findMoviesByTitle(String title) {
        return movieRepository.findMoviesByTitle(title);
    }

    @Override
    public List<MovieEntity> findMoviesByYear(int year) {
        return movieRepository.findMoviesByYear(year);
    }

    @Override
    public List<MovieEntity> findMoviesByRating(double rating) {
        return movieRepository.findMoviesByRating(rating);
    }

    @Override
    public List<MovieEntity> findMoviesByRatingRange(double minRating, double maxRating) {
        return movieRepository.findMoviesByRatingRange(minRating, maxRating);
    }

    @Override
    public MovieEntity updateMovieRating(MovieEntity movieEntity, double rating) {
        Double totalRating = movieEntity.getAccumulatedRating();
        Integer totalReviews = movieEntity.getTotalReviews();
        if (totalReviews == null || totalRating == null) {
            totalRating = 0.0;
            totalReviews = 1;
        } else {
            totalRating += rating;
            totalReviews++;
        }
        Double newRating = totalRating / totalReviews;

        movieEntity.setAccumulatedRating(totalRating);
        movieEntity.setTotalReviews(totalReviews);
        movieEntity.setAverageRating(newRating);

        return movieRepository.save(movieEntity);
    }
}
