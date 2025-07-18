package com.example.preferenceservice.controllers;

import com.example.preferenceservice.dtos.CreateReviewDto;
import com.example.preferenceservice.dtos.ReviewDto;
import com.example.preferenceservice.models.Review;
import com.example.preferenceservice.response.PageableResponse;
import com.example.preferenceservice.services.ReviewService;

import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
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
    public Review save(@Valid() @RequestBody() CreateReviewDto input) {
        return this.reviewService.save(input);
    }

    @GetMapping("/")
    public ResponseEntity<PageableResponse<ReviewDto>> getAllReviews(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        PageableResponse<ReviewDto> allReviews = this.reviewService.getAllReviews(pageable);
        return ResponseEntity.ok(allReviews);
    }
}
