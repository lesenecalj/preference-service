package com.example.preferenceservice.dtos;

import com.example.preferenceservice.models.Review;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder()
public class OutputReviewDto extends InputCreateReviewDto {
    private UUID id;

    public static OutputReviewDto fromEntity(Review review) {
        return OutputReviewDto.builder()
                .userId(review.getUserId())
                .movieId(review.getMovieId())
                .review(review.getReview())
                .id(review.getId())
                .build();
    }
}
