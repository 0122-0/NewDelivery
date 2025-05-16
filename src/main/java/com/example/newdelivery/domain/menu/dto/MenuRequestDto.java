package com.example.newdelivery.domain.menu.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MenuRequestDto {

    private final Long storeId;

    @NotBlank(message = "메뉴명을 입력해주세요.")
    private final String name;

    @NotBlank(message = "메뉴소개를 입력해주세요.")
    private final String content;

    @NotNull(message = "가격을 입력해주세요.")
    private final Integer price;

    public MenuRequestDto(Long storeId, String name, String content, Integer price) {
        this.storeId = storeId;
        this.name = name;
        this.content = content;
        this.price = price;
    }
}
