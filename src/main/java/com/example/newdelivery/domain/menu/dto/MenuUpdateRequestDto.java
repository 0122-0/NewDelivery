package com.example.newdelivery.domain.menu.dto;


import lombok.Getter;

@Getter
public class MenuUpdateRequestDto {
    private final String name;
    private final String content;
    private final Integer price;

    public MenuUpdateRequestDto(String name, String content, Integer price) {
        this.name = name;
        this.content = content;
        this.price = price;
    }
}
