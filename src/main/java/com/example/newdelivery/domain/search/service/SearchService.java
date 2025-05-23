package com.example.newdelivery.domain.search.service;

import com.example.newdelivery.domain.menu.repository.MenuRepository;
import com.example.newdelivery.domain.review.dto.ReviewResponseDto;
import com.example.newdelivery.domain.review.entity.Review;
import com.example.newdelivery.domain.review.repository.ReviewRepository;
import com.example.newdelivery.domain.search.entity.SearchKeyword;
import com.example.newdelivery.domain.search.repository.SearchKeywordRepository;
import com.example.newdelivery.global.exception.ApiException;
import com.example.newdelivery.global.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ReviewRepository reviewRepository;
    private final MenuRepository menuRepository;
    private final SearchKeywordRepository searchKeywordRepository;

    // v1: 캐시 없는 검색
    public Page<ReviewResponseDto> searchReviewsV1(String keyword, Pageable pageable) {
        String pattern = "%" + keyword + "%";
        Page<Review> reviews = reviewRepository.searchByContentLike(pattern, pageable);

        return reviews.map(review -> {
            String menuName = menuRepository.findById(review.getMenu().getId())
                    .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorType.NO_RESOURCE, "해당 메뉴 없음"))
                    .getName();

            return ReviewResponseDto.from(review, menuName);
        });
    }

    // v2: 캐시 적용 + 인기 키워드 저장
    @Cacheable(value = "reviewSearchCache", key = "#keyword + '_' + #pageable.pageNumber")  // 키와 벨류가 어디에 사용이 되는 건지 알아야 한다
    public Page<ReviewResponseDto> searchReviewsV2(String keyword, Pageable pageable) {
        String pattern = "%" + keyword + "%";
        Page<Review> reviews = reviewRepository.searchByContentLike(pattern, pageable);

        saveOrUpdateKeyword(keyword);

        return reviews.map(review -> {
            String menuName = menuRepository.findById(review.getMenu().getId())
                    .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorType.NO_RESOURCE, "해당 메뉴 없음"))
                    .getName();

            return ReviewResponseDto.from(review, menuName);
        });
    }

    // 인기 검색어 저장
    @Transactional
    public void saveOrUpdateKeyword(String keyword) {
        searchKeywordRepository.findById(keyword)
                .ifPresentOrElse(SearchKeyword::increaseCount, () ->
                        searchKeywordRepository.save(new SearchKeyword(keyword)));
    }

    // 인기 검색어 조회
    public List<String> getTop10Keywords() {
        return searchKeywordRepository.findTop10ByOrderByCountDesc().stream()
                .map(SearchKeyword::getKeyword)
                .toList();
    }
}