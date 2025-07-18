package com.example.preferenceservice.dtos;

import com.example.preferenceservice.models.Review;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder()
public class ReviewDto extends CreateReviewDto {
    private UUID id;

    public static ReviewDto fromEntity(Review review) {
        return ReviewDto.builder()
                .userId(review.getUserId())
                .movieId(review.getMovieId())
                .review(review.getReview())
                .id(review.getId())
                .build();
    }
}
