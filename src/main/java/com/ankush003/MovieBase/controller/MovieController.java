package com.ankush003.MovieBase.controller;

import com.ankush003.MovieBase.mappers.Mapper;
import com.ankush003.MovieBase.model.dto.MovieDto;
import com.ankush003.MovieBase.model.dto.ReviewDto;
import com.ankush003.MovieBase.model.entities.MovieEntity;
import com.ankush003.MovieBase.model.entities.ReviewEntity;
import com.ankush003.MovieBase.repository.MovieRepository;
import com.ankush003.MovieBase.repository.ReviewRepository;
import com.ankush003.MovieBase.repository.UserRepository;
import com.ankush003.MovieBase.service.MovieService;
import com.ankush003.MovieBase.service.ReviewService;
import com.ankush003.MovieBase.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Log
@RequestMapping("/api/movies")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {
    private MovieService movieService;
    private ReviewService reviewService;

    private final Mapper<MovieEntity, MovieDto> movieMapper;
    private final Mapper<ReviewEntity, ReviewDto> reviewMapper;

    @Autowired
    public MovieController(
            MovieService movieService,
            ReviewService reviewService,
            Mapper<MovieEntity, MovieDto> movieMapper,
            Mapper<ReviewEntity, ReviewDto> reviewMapper
    ) {
        log.info("MovieController created");
        this.movieService = movieService;
        this.reviewService = reviewService;
        this.movieMapper = movieMapper;
        this.reviewMapper = reviewMapper;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        log.info("Getting all movies");
        return ResponseEntity.ok(movieMapper.mapTo(movieService.getAllMovies()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id) {
        log.info("Getting movie by id");
        Optional<MovieEntity> movie = movieService.getMovieById(id);
        return movie.map(movieEntity -> ResponseEntity.ok(movieMapper.mapTo(movieEntity))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/genre")
    public ResponseEntity<List<MovieDto>> getMoviesByGenre(@RequestParam("genre") String genre) {
        log.info("Getting movies by genre");
        return ResponseEntity.ok(movieMapper.mapTo(movieService.findMoviesByGenre(genre)));
    }

    @GetMapping("/title")
    public ResponseEntity<List<MovieDto>> getMoviesByTitle(@RequestParam("title") String title) {
        log.info("Getting movies by title");
        return ResponseEntity.ok(movieMapper.mapTo(movieService.findMoviesByTitle(title)));
    }

    @GetMapping("/year")
    public ResponseEntity<List<MovieDto>> getMoviesByYear(@RequestParam("year") int year) {
        log.info("Getting movies by year");
        List<MovieDto> movies = movieMapper.mapTo(movieService.findMoviesByYear(year));
        if (movies.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/rating")
    public ResponseEntity<List<MovieDto>> getMoviesByRating(@RequestParam("rating") double rating) {
        log.info("Getting movies by rating");
        return ResponseEntity.ok(movieMapper.mapTo(movieService.findMoviesByRating(rating)));
    }

    @GetMapping("/rating-range")
    public ResponseEntity<List<MovieDto>> getMoviesByRatingRange(
            @RequestParam("minRating") double minRating,
            @RequestParam("maxRating") double maxRating) {
        log.info("Getting movies by rating range");
        return ResponseEntity.ok(movieMapper.mapTo(movieService.findMoviesByRatingRange(minRating, maxRating)));
    }

    @PostMapping("/new")
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movie) {
        log.info("Adding new movie");
        return ResponseEntity.ok(movieMapper.mapTo(movieService.addMovie(movieMapper.mapFrom(movie))));
        // sample json
        // {
        //     "title": "Animal",
        //     "releaseDate": "2023-12-21",
        //     "genre": "Crime, Action, Drama",
        //     "posterUrl": "https://m.media-amazon.com/images/I/71e1JzgZGTL._AC_SY679_.jpg",
        //     "director": "Roger Allers, Rob Minkoff"
        // }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        log.info("Deleting movie");
        Optional<MovieEntity> movie = movieService.getMovieById(id);
        if (movie.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        movieService.deleteMovie(id);

        return ResponseEntity.ok("Movie Id: " + id + " deleted");
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<List<ReviewDto>> getMovieReviews(@PathVariable Long id) {
        log.info("Getting reviews for movie");
        List<ReviewEntity> reviews = reviewService.findReviewsByMovieId(id);
        if (reviews.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviewMapper.mapTo(reviews));
    }
}
