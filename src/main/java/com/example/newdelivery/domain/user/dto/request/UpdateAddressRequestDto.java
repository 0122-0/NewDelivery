package com.example.newdelivery.domain.user.dto.request;

import lombok.Getter;

@Getter
public class UpdateAddressRequestDto {

    private final String address;

    public UpdateAddressRequestDto(String address) {
        this.address = address;
    }
}
