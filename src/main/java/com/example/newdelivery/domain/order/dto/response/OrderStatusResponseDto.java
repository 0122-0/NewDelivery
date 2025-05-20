package com.example.newdelivery.domain.order.dto.response;

import com.example.newdelivery.domain.order.entity.Order;
import com.example.newdelivery.domain.order.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderStatusResponseDto {
    private Long id;
    private Long storeId;
    private OrderStatus orderStatus;

   public static OrderStatusResponseDto from(Order order) {
       return new OrderStatusResponseDto(
               order.getId(),
               order.getStore().getId(),
               order.getOrderstatus()
       );
   }
}
