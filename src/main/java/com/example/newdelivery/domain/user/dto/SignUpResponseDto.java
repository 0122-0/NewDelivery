package com.example.newdelivery.domain.user.dto;

import lombok.Getter;

@Getter
public class SignUpResponseDto {

    private final String email;
    private final String password;
    private final String name;
    private final String nickName;
    private final String phone;
    private final String address;
    private final String role;

    public SignUpResponseDto(String email, String password, String name, String nickName, String phone, String address, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }
}
