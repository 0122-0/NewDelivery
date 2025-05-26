package com.example.newdelivery.domain.store.Dto;


import com.example.newdelivery.domain.store.Entity.Store;
import com.example.newdelivery.domain.user.entity.User;
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

    public StoreRequestDto(Store store) {
        this.name = store.getName();
        this.content = store.getContent();
        this.address = store.getAddress();
        this.openTime = store.getOpenTime();
        this.closeTime = store.getCloseTime();
    }

    public Store toEntity(User owner){
        return Store.builder()
                .name(this.name)
                .content(this.content)
                .address(this.address)
                .openTime(this.openTime)
                .closeTime(this.closeTime)
                .owner(owner)
                .build();
    }

}
