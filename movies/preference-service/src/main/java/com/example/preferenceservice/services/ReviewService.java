package com.example.preferenceservice.services;

import com.example.preferenceservice.dtos.ReviewDto;
import com.example.preferenceservice.models.Review;
import com.example.preferenceservice.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> findByUserId(Long userId) {
        return this.reviewRepository.findByUserId(userId);
    }

    public Review save(ReviewDto reviewDto) {
        Review review = Review.builder()
                .movieId(reviewDto.getMovieId())
                .review(reviewDto.getReview())
                .userId(reviewDto.getUserId())
                .build();
        return this.reviewRepository.save(review);
    }
}
