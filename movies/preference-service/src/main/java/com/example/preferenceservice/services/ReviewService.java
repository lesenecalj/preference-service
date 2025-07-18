package com.example.preferenceservice.services;

import com.example.preferenceservice.dtos.InputCreateReviewDto;
import com.example.preferenceservice.dtos.OutputReviewDto;
import com.example.preferenceservice.dtos.response.OutputResponse;
import com.example.preferenceservice.models.Review;
import com.example.preferenceservice.repositories.ReviewRepository;
import com.example.preferenceservice.dtos.response.PageableMetadata;
import com.example.preferenceservice.dtos.response.PageableOutputResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public OutputResponse<OutputReviewDto> save(InputCreateReviewDto reviewDto) {
        Review review = Review.builder()
                .movieId(reviewDto.getMovieId())
                .review(reviewDto.getReview())
                .userId(reviewDto.getUserId())
                .build();
        Review createdReview = this.reviewRepository.save(review);
        return new OutputResponse<>(OutputReviewDto.fromEntity(createdReview));
    }

    public PageableOutputResponse<OutputReviewDto> getReviewsByUserId(Long userId, Pageable pageable) {
        Page<Review> userReviewsPerPage = this.reviewRepository.findByUserId(userId, pageable);
        List<OutputReviewDto> userReviewsDtoPerPage = userReviewsPerPage.getContent().stream().map(OutputReviewDto::fromEntity).toList();
                PageableMetadata pageableMetadata = new PageableMetadata(
                userReviewsPerPage.getNumber(),
                userReviewsPerPage.getSize(),
                userReviewsPerPage.getTotalElements(),
                userReviewsPerPage.getTotalPages()
        );
        return new PageableOutputResponse<>(userReviewsDtoPerPage, pageableMetadata);
    }

    public PageableOutputResponse<OutputReviewDto> getAllReviews(Pageable pageable) {
        Page<Review> reviewsPerPage = this.reviewRepository.findAll(pageable);
        List<OutputReviewDto> reviews = reviewsPerPage.getContent().stream().map(OutputReviewDto::fromEntity).toList();
        PageableMetadata pageableMetadata = new PageableMetadata(
                reviewsPerPage.getNumber(),
                reviewsPerPage.getSize(),
                reviewsPerPage.getTotalElements(),
                reviewsPerPage.getTotalPages()
        );
        return new PageableOutputResponse<>(reviews, pageableMetadata);
    }
}
