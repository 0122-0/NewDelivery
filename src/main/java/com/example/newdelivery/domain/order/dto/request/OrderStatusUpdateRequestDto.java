package com.example.newdelivery.domain.order.dto.request;

import com.example.newdelivery.domain.order.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusUpdateRequestDto {

    @NotNull(message = "변경할 주문 상태를 입력해주세요.")
    private OrderStatus orderStatus;
}
