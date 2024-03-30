package com.ankush003.MovieBase.controller;

import com.ankush003.MovieBase.mappers.Mapper;
import com.ankush003.MovieBase.model.dto.ReviewDto;
import com.ankush003.MovieBase.model.entities.MovieEntity;
import com.ankush003.MovieBase.model.entities.ReviewEntity;
import com.ankush003.MovieBase.model.entities.UserEntity;
import com.ankush003.MovieBase.repository.ReviewRepository;
import com.ankush003.MovieBase.repository.UserRepository;
import com.ankush003.MovieBase.service.MovieService;
import com.ankush003.MovieBase.service.ReviewService;
import com.ankush003.MovieBase.service.UserService;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private ReviewService reviewService;
    private ReviewRepository reviewRepository;
    private UserService userService;
    private MovieService movieService;
    private Mapper<ReviewEntity, ReviewDto> reviewMapper;

    @Autowired
    public ReviewController(ReviewService reviewService, ReviewRepository reviewRepository, UserService userService, MovieService movieService, Mapper<ReviewEntity, ReviewDto> reviewMapper) {
        this.reviewService = reviewService;
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.movieService = movieService;
        this.reviewMapper = reviewMapper;
    }

    @PostMapping("/{userId}/{movieId}")
    public ResponseEntity<ReviewDto> addReview(@RequestBody ReviewDto review, @PathVariable Long userId, @PathVariable Long movieId) {
        ReviewEntity reviewEntity = reviewMapper.mapFrom(review);
        Optional<UserEntity> user = userService.getUserById(userId);
        Optional<MovieEntity> movie = movieService.getMovieById(movieId);

        if (user.isEmpty() || movie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        reviewEntity.setUser(user.get());
        reviewEntity.setMovie(movie.get());
        return ResponseEntity.ok(reviewMapper.mapTo(reviewService.addReview(reviewEntity)));

        // sample json
        // {
        //     "rating": 5,
        //     "review": "This is a great movie"
        // }
    }
}
