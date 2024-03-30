package com.ankush003.MovieBase.service;

import com.ankush003.MovieBase.model.entities.MovieEntity;
import com.ankush003.MovieBase.model.entities.ReviewEntity;
import com.ankush003.MovieBase.model.entities.UserEntity;

import java.util.List;

public interface ReviewService {
    ReviewEntity addReview(ReviewEntity reviewEntity, UserEntity user, MovieEntity movie);
//    ReviewEntity getReviewById(Long id);
//    List<ReviewEntity> getAllReviews();
//    ReviewEntity updateReview(ReviewEntity reviewEntity);
//    void deleteReview(Long id);
    List<ReviewEntity> findReviewsByMovieId(Long movieId);
    List<ReviewEntity> findReviewsByUserId(Long userId);

    void deleteReview(Long id);

    ReviewEntity updateReview(ReviewEntity reviewEntity);

    List<ReviewEntity> getAllReviews();
}
