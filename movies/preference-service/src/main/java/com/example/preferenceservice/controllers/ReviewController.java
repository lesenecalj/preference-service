package com.example.preferenceservice.controllers;

import com.example.preferenceservice.dtos.InputCreateReviewDto;
import com.example.preferenceservice.dtos.OutputReviewDto;
import com.example.preferenceservice.dtos.response.PageableResponse;
import com.example.preferenceservice.models.Review;
import com.example.preferenceservice.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/")
    public OutputReviewDto save(@Valid() @RequestBody() InputCreateReviewDto input) {
        Review review = this.reviewService.save(input);
        return OutputReviewDto.fromEntity(review);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<PageableResponse<OutputReviewDto>> getReviewsByUserId(
            @PathVariable("userId") Long userId,
            Pageable pageable
    ) {
        PageableResponse<OutputReviewDto> allReviews = this.reviewService.getReviewsByUserId(userId, pageable);
        return ResponseEntity.ok(allReviews);
    }

    @GetMapping("/")
    public ResponseEntity<PageableResponse<OutputReviewDto>> getAllReviews(Pageable pageable) {
        PageableResponse<OutputReviewDto> allReviews = this.reviewService.getAllReviews(pageable);
        return ResponseEntity.ok(allReviews);
    }
}
