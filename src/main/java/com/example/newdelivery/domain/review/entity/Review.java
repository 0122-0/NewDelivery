package com.example.newdelivery.domain.review.entity;

import com.example.newdelivery.common.baseEntity.BaseEntity;
import com.example.newdelivery.domain.menu.entity.Menu;
import com.example.newdelivery.domain.order.entity.Order;
import com.example.newdelivery.domain.store.Entity.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.newdelivery.domain.user.entity.User;



@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private int rating;

	@Column(nullable = false)
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="store_id")
	private Store store;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="menu_id")
	private Menu menu;

}
