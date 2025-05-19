package com.example.newdelivery.domain.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    @NotBlank(message = "Email은 필수 입력 값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private final String email;

    @NotBlank(message = "비밀번호는 필수 입력값 입니다.")
    private final String password;

    @NotBlank(message = "이름은 필수 입력값 입니다.")
    private final String name;

    @NotBlank(message = "별명은 필수 입력값 입니다.")
    private final String nickName;

    @NotBlank(message = "전화번호는 필수 입력값 입니다.")
    private final String phone;

    @NotBlank(message = "주소는 필수 입력값 입니다.")
    private final String address;

    @NotBlank(message = "유저 유형은 필수 입력값 입니다.")
    private final String Role;


    public SignUpRequestDto(String email, String password, String name, String nickName, String phone, String address, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.address = address;
        this.Role = role;
    }
}
