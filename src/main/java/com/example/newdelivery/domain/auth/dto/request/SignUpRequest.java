package com.example.newdelivery.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "Email은 필수 입력 값입니다.")
     private String email;

    @NotBlank(message = "비밀번호는 필수 입력값 입니다.")
    private String password;

    @NotBlank(message = "이름은 필수 입력값 입니다.")
    private String name;

    @NotBlank(message = "별명은 필수 입력값 입니다.")
    private String nickName;

    @NotBlank(message = "전화번호는 필수 입력값 입니다.")
    private String phone;

    @NotBlank(message = "주소는 필수 입력값 입니다.")
    private String address;

    @NotBlank(message = "유저 유형은 필수 입력값 입니다.")
    private String role;


}
