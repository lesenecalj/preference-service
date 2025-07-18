package com.example.preferenceservice.controllers;

import com.example.preferenceservice.dtos.InputCreateReviewDto;
import com.example.preferenceservice.dtos.OutputReviewDto;
import com.example.preferenceservice.dtos.response.OutputResponse;
import com.example.preferenceservice.dtos.response.PageableOutputResponse;
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
    public ResponseEntity<OutputResponse<OutputReviewDto>> save(@Valid() @RequestBody() InputCreateReviewDto input) {
        OutputResponse<OutputReviewDto> createdUserDto = this.reviewService.save(input);
        return ResponseEntity.ok(createdUserDto);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<PageableOutputResponse<OutputReviewDto>> getReviewsByUserId(
            @PathVariable("userId") Long userId,
            Pageable pageable
    ) {
        PageableOutputResponse<OutputReviewDto> allReviews = this.reviewService.getReviewsByUserId(userId, pageable);
        return ResponseEntity.ok(allReviews);
    }

    @GetMapping("/")
    public ResponseEntity<PageableOutputResponse<OutputReviewDto>> getAllReviews(Pageable pageable) {
        PageableOutputResponse<OutputReviewDto> allReviews = this.reviewService.getAllReviews(pageable);
        return ResponseEntity.ok(allReviews);
    }
}
