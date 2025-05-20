package com.example.newdelivery.domain.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    private Long storeId;
    private Long menuId;
    private int quantity;
    private int totalPrice;
}
