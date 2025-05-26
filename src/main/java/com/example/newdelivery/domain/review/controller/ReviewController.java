package com.example.newdelivery.domain.review.controller;

import com.example.newdelivery.common.security.CustomUserPrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.newdelivery.domain.review.dto.ReviewRequestDto;
import com.example.newdelivery.domain.review.dto.ReviewResponseDto;
import com.example.newdelivery.domain.review.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class ReviewController {
    private final ReviewService reviewService;


    @PostMapping("/orders/{orderId}/reviews")
    public ResponseEntity<ReviewResponseDto> createReview(
            @AuthenticationPrincipal CustomUserPrincipal user,
            @PathVariable Long orderId,
            @RequestBody ReviewRequestDto reviewRequestDto
            ) {

        ReviewResponseDto responseDto = reviewService.createReview(orderId, reviewRequestDto, user.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/stores/{storeId}/reviews")
    public ResponseEntity<Page<ReviewResponseDto>> getStoreReviews(
            @AuthenticationPrincipal CustomUserPrincipal user,
            @PathVariable Long storeId,
            @RequestParam(required = false) Integer minRating,
            @RequestParam(required = false) Integer maxRating,
            Pageable pageable
    ) {

        Page<ReviewResponseDto> reviews = reviewService.getStoreReviews(storeId, minRating, maxRating, pageable, user.getId());

        return ResponseEntity.ok(reviews);
    }
}