package com.example.preferenceservice.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewDto {
    @NotNull(message = "userId is required")
    private Long userId;

    @NotNull(message = "movieId is required")
    private String MovieId;

    @NotNull(message = "review is required")
    @Min(0) @Max(10)
    private Float review;
}
