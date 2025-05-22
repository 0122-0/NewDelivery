package com.example.newdelivery.domain.order.controller;

import com.example.newdelivery.common.security.CustomUserPrincipal;
import com.example.newdelivery.domain.order.dto.request.OrderCreateRequest;
import com.example.newdelivery.domain.order.dto.request.OrderStatusUpdateRequestDto;
import com.example.newdelivery.domain.order.dto.response.OrderResponseDto;
import com.example.newdelivery.domain.order.dto.response.OrderStatusResponseDto;
import com.example.newdelivery.domain.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@AuthenticationPrincipal CustomUserPrincipal user, @Valid @RequestBody OrderCreateRequest dto) {
        return ResponseEntity.ok(orderService.createOrder(user.getId(), dto));
    }

    @PatchMapping("/{orderId}/cancel")
    public ResponseEntity<OrderStatusResponseDto> cancelOrder(@AuthenticationPrincipal CustomUserPrincipal user, @PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.cancelOrder(user.getId(),orderId));
    }

    @PatchMapping("/{orderId}/status")
    public ResponseEntity<OrderStatusResponseDto> changeOrderStatus(@AuthenticationPrincipal CustomUserPrincipal user,@PathVariable Long orderId, @Valid @RequestBody OrderStatusUpdateRequestDto dto) {
        return ResponseEntity.ok(orderService.changeOrderStatus(user,orderId, dto.getOrderStatus()));
    }
}
