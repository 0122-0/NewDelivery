package com.example.newdelivery.domain.search.controller;

import com.example.newdelivery.domain.review.dto.ReviewResponseDto;
import com.example.newdelivery.domain.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/v1/reviews/search")
    public ResponseEntity<Page<ReviewResponseDto>> searchReviewsV1(
            @RequestParam String keyword,
            Pageable pageable
    ) {
        return ResponseEntity.ok(searchService.searchReviewsV1(keyword, pageable));
    }

    @GetMapping("/v2/reviews/search")
    public ResponseEntity<Page<ReviewResponseDto>> searchReviewsV2(
            @RequestParam String keyword,
            Pageable pageable
    ) {
        return ResponseEntity.ok(searchService.searchReviewsV2(keyword, pageable));
    }

    @GetMapping("/v2/reviews/popular-keywords")
    public ResponseEntity<List<String>> getPopularKeywords() {
        return ResponseEntity.ok(searchService.getTop10Keywords());
    }
}
