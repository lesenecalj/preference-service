package com.example.preferenceservice.services;

import com.example.preferenceservice.dtos.CreateReviewDto;
import com.example.preferenceservice.dtos.ReviewDto;
import com.example.preferenceservice.models.Review;
import com.example.preferenceservice.repositories.ReviewRepository;
import com.example.preferenceservice.response.PageableMetadata;
import com.example.preferenceservice.response.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> findByUserId(Long userId) {
        return this.reviewRepository.findByUserId(userId);
    }

    public Review save(CreateReviewDto reviewDto) {
        Review review = Review.builder()
                .movieId(reviewDto.getMovieId())
                .review(reviewDto.getReview())
                .userId(reviewDto.getUserId())
                .build();
        return this.reviewRepository.save(review);
    }

    public PageableResponse<ReviewDto> getAllReviews(Pageable pageable) {
        Page<Review> reviewsPerPage = this.reviewRepository.findAll(pageable);
        List<ReviewDto> reviews = reviewsPerPage.getContent().stream().map(ReviewDto::fromEntity).toList();
        PageableMetadata pageableMetadata = new PageableMetadata(
                reviewsPerPage.getNumber(),
                reviewsPerPage.getSize(),
                reviewsPerPage.getTotalElements(),
                reviewsPerPage.getTotalPages()
        );
        return new PageableResponse<ReviewDto>(reviews, pageableMetadata);
    }
}
