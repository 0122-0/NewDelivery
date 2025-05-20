package com.example.newdelivery.domain.store.Dto;


import com.example.newdelivery.domain.store.Entity.Store;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor

public class StoreRequestDto {

    @NotBlank(message = "가게명을 입력해주세요")
    private String name;

    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    @NotBlank(message = "주소를 입력해주세요")
    private String address;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime openTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime closeTime;

    @Min(value = 15000, message = "최소 주문 금액은 15000이상이여야 합니다.")
    private Long minimumOrder;

    public StoreRequestDto(Store store) {
        this.name = store.getName();
        this.content = store.getContent();
        this.address = store.getAddress();
        this.openTime = store.getOpenTime();
        this.closeTime = store.getCloseTime();
        this.minimumOrder = store.getMinimumOrder();
    }

}
