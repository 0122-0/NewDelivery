package com.example.newdelivery.domain.review.dto;


import java.time.LocalDateTime;

import com.example.newdelivery.domain.review.entity.Review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {

	private Long id;

	private Long orderId;

	private int rating;

	private String content;

	private LocalDateTime createdAt;

	public static ReviewResponseDto from(Review review, String menuName) {
		return ReviewResponseDto.builder()
			.id(review.getId())
			.orderId(review.getOrderId())
			.rating(review.getRating())
			.content(review.getContent())
			.createdAt(review.getCreatedAt())
			.build();
	}
}
