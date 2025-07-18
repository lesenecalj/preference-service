package com.example.preferenceservice.services;

import com.example.preferenceservice.dtos.InputCreateReviewDto;
import com.example.preferenceservice.dtos.OutputReviewDto;
import com.example.preferenceservice.models.Review;
import com.example.preferenceservice.repositories.ReviewRepository;
import com.example.preferenceservice.dtos.response.PageableMetadata;
import com.example.preferenceservice.dtos.response.PageableResponse;
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

    public Review save(InputCreateReviewDto reviewDto) {
        Review review = Review.builder()
                .movieId(reviewDto.getMovieId())
                .review(reviewDto.getReview())
                .userId(reviewDto.getUserId())
                .build();
        return this.reviewRepository.save(review);
    }

    public PageableResponse<OutputReviewDto> getAllReviews(Pageable pageable) {
        Page<Review> reviewsPerPage = this.reviewRepository.findAll(pageable);
        List<OutputReviewDto> reviews = reviewsPerPage.getContent().stream().map(OutputReviewDto::fromEntity).toList();
        PageableMetadata pageableMetadata = new PageableMetadata(
                reviewsPerPage.getNumber(),
                reviewsPerPage.getSize(),
                reviewsPerPage.getTotalElements(),
                reviewsPerPage.getTotalPages()
        );
        return new PageableResponse<>(reviews, pageableMetadata);
    }
}
