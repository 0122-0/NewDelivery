package com.example.newdelivery.domain.user.enums;

import com.example.newdelivery.global.exception.ApiException;
import com.example.newdelivery.global.exception.ErrorType;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public enum Role {

    USER, OWNER, ADMIN;

    public static Role of(String role) {

        return Arrays.stream(Role.values())
                .filter(r -> r.name().equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorType.INVALID_ROLE,"권한이 다른 사용자 입니다."));
    }
}
