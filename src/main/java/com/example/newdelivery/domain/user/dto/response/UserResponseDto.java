package com.example.newdelivery.domain.user.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {

    private final String email;
    private final String name;
    private final String nickName;
    private final String phone;
    private final String address;
    private final String role;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public UserResponseDto(String email, String name, String nickName, String phone, String address, String role, LocalDateTime createdAt, LocalDateTime updatedAt) {

        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
