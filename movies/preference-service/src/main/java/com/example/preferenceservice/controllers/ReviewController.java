package com.example.preferenceservice.controllers;

import com.example.preferenceservice.dtos.ReviewDto;
import com.example.preferenceservice.models.Review;
import com.example.preferenceservice.services.ReviewService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/")
    public Review save(@Valid() @RequestBody() ReviewDto input) {
        return this.reviewService.save(input);
    }
}
