package com.example.newdelivery.domain.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.newdelivery.domain.review.entity.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	boolean existsByOrderId(Long orderId);

	Page<Review> findByStoreIdAndRatingBetweenOrderByCreatedAtDesc(Long storeId, int minRating, int maxRating, Pageable pageable);

	boolean existsByStoreId(Long storeId);

	@Query("SELECT r FROM Review r WHERE r.content LIKE :keyword")
	Page<Review> searchByContentLike(@Param("keyword") String keyword, Pageable pageable);
}
