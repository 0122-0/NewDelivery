package com.example.newdelivery.domain.order.repository;

import com.example.newdelivery.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
