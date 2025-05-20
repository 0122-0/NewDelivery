package com.example.newdelivery.domain.store.Dto;

import com.example.newdelivery.domain.store.Entity.Store;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter

public class StoreResponseDto {
    private Long id;
    private String name;
    private String content;
    private String address;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Long minimumOder;

    public StoreResponseDto(Store store){
        this.id = store.getId();
        this.name = store.getName();
        this.content = store.getContent();
        this.address = store.getAddress();
        this.openTime = store.getOpenTime();
        this.closeTime = store.getCloseTime();
        this.minimumOder = store.getMinimumOrder();

    }
}
