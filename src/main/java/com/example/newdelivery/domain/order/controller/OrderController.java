package com.example.newdelivery.domain.order.controller;

import com.example.newdelivery.domain.order.dto.request.OrderCreateRequest;
import com.example.newdelivery.domain.order.dto.request.OrderStatusUpdateRequestDto;
import com.example.newdelivery.domain.order.dto.response.OrderResponseDto;
import com.example.newdelivery.domain.order.dto.response.OrderStatusResponseDto;
import com.example.newdelivery.domain.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@PathVariable Long userId, @Valid @RequestBody OrderCreateRequest dto) {
        return ResponseEntity.ok(orderService.createOrder(userId, dto));
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<OrderStatusResponseDto> cancelOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<OrderStatusResponseDto> changeOrderStatus(@PathVariable Long orderId, @Valid @RequestBody OrderStatusUpdateRequestDto dto) {
        return ResponseEntity.ok(orderService.changeOrderStatus(orderId, dto.getOrderStatus()));
    }
}
