package com.example.newdelivery.domain.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderResponseDto {
    private Long id;
    private Long storeId;
    private Long menuId;
    private int  Price;
    private LocalDateTime updatedAt;
}
