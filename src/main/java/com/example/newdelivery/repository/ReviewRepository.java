package com.example.newdelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.newdelivery.domain.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	boolean existsByOrderId(Long orderId);

}
