package com.ankush003.MovieBase.service.impl;

import com.ankush003.MovieBase.model.entities.ReviewEntity;
import com.ankush003.MovieBase.repository.ReviewRepository;
import com.ankush003.MovieBase.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ReviewEntity addReview(ReviewEntity reviewEntity) {
        return reviewRepository.save(reviewEntity);
    }
}
