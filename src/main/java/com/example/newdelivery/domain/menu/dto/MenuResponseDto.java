package com.example.newdelivery.domain.menu.dto;

import com.example.newdelivery.domain.menu.entity.Menu;
import lombok.Getter;

@Getter
public class MenuResponseDto {

    private final Long id;

    private final Long storeId;

    private final String name;

    private final String content;

    private final Integer price;

    public MenuResponseDto(Long id, Long storeId, String name, String content, Integer price) {
        this.id = id;
        this.storeId = storeId;
        this.name = name;
        this.content = content;
        this.price = price;
    }

    /*
    가독성 + 중복 제거 + 유지보수 편리
     */
    public static MenuResponseDto toDto(Menu menu) {
        return new MenuResponseDto(
                menu.getId(),
                menu.getStore().getId(),
                menu.getName(),
                menu.getContent(),
                menu.getPrice()
        );
    }

}
