package com.example.newdelivery.domain.user.controller;

import com.example.newdelivery.domain.user.dto.SignUpRequestDto;
import com.example.newdelivery.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    //회원 가입
    @PostMapping("/signup")
    public ResponseEntity<SignUpRequestDto> signUp (@Valid @RequestBody SignUpRequestDto requestDto){

        SignUpRequestDto signUpRequestDto = userService.signUp;

        return ResponseEntity
    }
}