package com.example.newdelivery.domain.store.Dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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
    private LocalDate openTime;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalDate closeTime;

    @Min(value = 15000, message = "최소 주문 금액은 15000이상이여야 합니다.")
    private Long minimumOder;
}
