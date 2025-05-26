package com.example.newdelivery.domain.review.service;


import com.example.newdelivery.domain.menu.entity.Menu;
import com.example.newdelivery.domain.menu.repository.MenuRepository;
import com.example.newdelivery.domain.order.entity.Order;
import com.example.newdelivery.domain.order.enums.OrderStatus;
import com.example.newdelivery.domain.order.repository.OrderRepository;
import com.example.newdelivery.domain.review.dto.ReviewRequestDto;
import com.example.newdelivery.domain.review.dto.ReviewResponseDto;
import com.example.newdelivery.domain.review.entity.Review;
import com.example.newdelivery.domain.review.repository.ReviewRepository;
import com.example.newdelivery.domain.store.Entity.Store;
import com.example.newdelivery.domain.store.Repository.StoreRepository;
import com.example.newdelivery.domain.user.entity.User;
import com.example.newdelivery.domain.user.repository.UserRepository;
import com.example.newdelivery.global.exception.ApiException;
import com.example.newdelivery.global.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final UserRepository userRepository;
	private final MenuRepository menuRepository;
	private final OrderRepository orderRepository;
	private final StoreRepository storeRepository;


	@Transactional
	public ReviewResponseDto createReview(Long orderId, ReviewRequestDto reviewRequestDto, Long userId) {

		User user  = userRepository.findByIdOrElseThrow(userId);

		Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


		if (reviewRepository.existsByOrderId(orderId)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 리뷰가 작성된 주문입니다.");
		}

		if (!order.getUser().getId().equals(userId)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "자신의 주문에 대해서만 리뷰를 작성할 수 있습니다.");
		}

		if (!OrderStatus.ARRIVED.equals(order.getOrderstatus())){
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "배달이 완료된 주문에 대해서만 리뷰를 작성할 수 있습니다.");
		}

		if (reviewRequestDto.getRating() < 1 || reviewRequestDto.getRating() > 5) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "별점은 1점에서 5점 사이여야 합니다.");
		}

		Menu menu = menuRepository.findById(reviewRequestDto.getMenuId()).orElseThrow(() ->
			new ApiException(HttpStatus.NOT_FOUND, ErrorType.NO_RESOURCE,"메뉴 ID가 잘못되었거나 없는 메뉴입니다."));

		Store store = storeRepository.findStoreByIdOrElseThrow(reviewRequestDto.getStoreId());

		Review review = Review.builder()
				.rating(reviewRequestDto.getRating())
				.content(reviewRequestDto.getContent())
				.menu(menu)
				.store(store)
				.user(user)
				.order(order)
				.build();

		Review savedReview = reviewRepository.save(review);

		return ReviewResponseDto.from(savedReview, menu.getName());
	}

	@Transactional(readOnly = true)
	public Page<ReviewResponseDto> getStoreReviews(Long storeId, Integer minRating, Integer maxRating, Pageable pageable, Long userId) {
		if (!reviewRepository.existsByStoreId(storeId)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 가게입니다.");
		}

		int min = minRating != null ? minRating : 1;
		int max = maxRating != null ? maxRating : 5;

		if (min < 1 || max > 5 || min > max) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 별점 범위입니다.");
		}

		Page<Review> reviews = reviewRepository.findByStoreIdAndRatingBetweenOrderByCreatedAtDesc(storeId, min, max, pageable);

		return reviews.map(review -> {
			Menu menu = menuRepository.findById(review.getMenu().getId()).orElseThrow(() ->
				new ApiException(HttpStatus.NOT_FOUND,ErrorType.NO_RESOURCE,"메뉴 ID가 잘못되었거나 없는 메뉴입니다."));
			return ReviewResponseDto.from(review, menu.getName());
		});
	}
}
