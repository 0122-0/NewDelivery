package com.example.newdelivery.domain.store.Dto;

import com.example.newdelivery.domain.menu.dto.response.MenuResponseDto;
import com.example.newdelivery.domain.store.Entity.Store;
import com.example.newdelivery.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StoreResponseDto {
    private Long id;
    private String name;
    private String content;
    private String address;
    private LocalTime openTime;
    private LocalTime closeTime;
    private List<MenuResponseDto> menus;
    private Long ownerId;
    private String ownerRole;

    public StoreResponseDto(Long id, String name, String content, String address, LocalTime openTime, LocalTime closeTime,
                            List<MenuResponseDto> menus, Long ownerId, String ownerRole) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.menus = menus;
        this.ownerId = ownerId;
        this.ownerRole = ownerRole;
    }

    public static StoreResponseDto from(Store store) {
        List<MenuResponseDto> menuDtos = store.getMenus() != null
                ? store.getMenus().stream()
                .map(MenuResponseDto::toDto)
                .collect(Collectors.toList())
                : new ArrayList<>();

        return new StoreResponseDto(
                store.getId(),
                store.getName(),
                store.getContent(),
                store.getAddress(),
                store.getOpenTime(),
                store.getCloseTime(),
                menuDtos,
                store.getOwner().getId(),
                store.getOwner().getRole().name()
        );
    }
}