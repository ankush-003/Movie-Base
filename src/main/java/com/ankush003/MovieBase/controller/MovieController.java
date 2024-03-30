package com.ankush003.MovieBase.controller;

import com.ankush003.MovieBase.mappers.Mapper;
import com.ankush003.MovieBase.model.dto.MovieDto;
import com.ankush003.MovieBase.model.entities.MovieEntity;
import com.ankush003.MovieBase.repository.MovieRepository;
import com.ankush003.MovieBase.repository.ReviewRepository;
import com.ankush003.MovieBase.repository.UserRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log
@RequestMapping("/movie")
public class MovieController {
    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    private final Mapper<MovieEntity, MovieDto> movieMapper;

    @Autowired
    public MovieController(
            MovieRepository movieRepository,
            ReviewRepository reviewRepository,
            UserRepository userRepository,
            Mapper<MovieEntity, MovieDto> movieMapper
    ) {
        log.info("MovieController created");
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.movieMapper = movieMapper;
    }

    @GetMapping("/all")
    public List<MovieDto> getAllMovies() {
        log.info("Getting all movies");
        return movieMapper.mapTo(movieRepository.findAll());
    }
}
