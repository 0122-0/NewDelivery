package com.example.newdelivery.domain.order.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {

    @NotNull(message = "가게 Id는 필수입니다.")
    private Long storeId;

    @NotNull(message = "메뉴 Id는 필수입니다.")
    private Long menuId;

    @Min(value = 1, message = "수량은 1개 이상이어야 합니다.")
    private Integer quantity;
}
