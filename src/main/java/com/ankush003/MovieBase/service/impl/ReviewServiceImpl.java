package com.ankush003.MovieBase.service.impl;

import com.ankush003.MovieBase.model.entities.MovieEntity;
import com.ankush003.MovieBase.model.entities.ReviewEntity;
import com.ankush003.MovieBase.model.entities.UserEntity;
import com.ankush003.MovieBase.repository.ReviewRepository;
import com.ankush003.MovieBase.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ReviewEntity addReview(ReviewEntity reviewEntity, UserEntity user, MovieEntity movie) {
        reviewEntity.setUser(user);
        reviewEntity.setMovie(movie);
        return reviewRepository.save(reviewEntity);
    }

    @Override
    public List<ReviewEntity> findReviewsByMovieId(Long movieId) {
        return reviewRepository.findReviewsByMovieId(movieId);
    }

    @Override
    public List<ReviewEntity> findReviewsByUserId(Long userId) {
        return reviewRepository.findReviewsByUserId(userId);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public ReviewEntity updateReview(ReviewEntity reviewEntity) {
        return reviewRepository.save(reviewEntity);
    }

    @Override
    public List<ReviewEntity> getAllReviews() {
        return reviewRepository.findAll();
    }
}
