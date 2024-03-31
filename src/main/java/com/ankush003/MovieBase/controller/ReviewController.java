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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {
    private ReviewService reviewService;
    private UserService userService;
    private MovieService movieService;
    private Mapper<ReviewEntity, ReviewDto> reviewMapper;

    @Autowired
    public ReviewController(ReviewService reviewService, UserService userService, MovieService movieService, Mapper<ReviewEntity, ReviewDto> reviewMapper) {
        this.reviewService = reviewService;
        this.userService = userService;
        this.movieService = movieService;
        this.reviewMapper = reviewMapper;
    }

    @PostMapping("/new")
    public ResponseEntity<ReviewDto> addReview(@RequestBody ReviewDto review, @RequestParam("userId") Long userId, @RequestParam("movieId") Long movieId) {
        ReviewEntity reviewEntity = reviewMapper.mapFrom(review);
        Optional<UserEntity> user = userService.getUserById(userId);
        Optional<MovieEntity> movie = movieService.getMovieById(movieId);

        if (user.isEmpty() || movie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        MovieEntity updatedMovie = movieService.updateMovieRating(movie.get(), reviewEntity.getRating());
        return ResponseEntity.ok(reviewMapper.mapTo(reviewService.addReview(reviewEntity, user.get(), updatedMovie)));

        // sample json
        // {
        //     "rating": 5,
        //     "review": "This is a great movie"
        // }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable("id") Long id) {
        Optional<ReviewEntity> review = reviewService.getReviewById(id);
        if (review.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Review Id: " + id + " deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        return ResponseEntity.ok(reviewMapper.mapTo(reviewService.getAllReviews()));
    }
}
