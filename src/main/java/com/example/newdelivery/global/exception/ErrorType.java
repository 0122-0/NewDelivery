package com.example.newdelivery.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorType {
    NO_RESOURCE("존재하지 않는 데이터입니다"),
    INVALID_PARAMETER("잘못된 파라미터 요청입니다"),
    UNKNOWN_ERROR("알 수 없는 에러입니다"),
    NOT_LOGGED_IN("로그인이 필요한 사용자입니다"),
    STORE_NOT_FOUND("존재하지 않는 가게입니다"),
    MENU_NOT_FOUND("해당 가게의 메뉴가 존재하지 않습니다"),
    INVALID_ROLE("권한이 다른 사용자 입니다.");




    private final String description;
}
