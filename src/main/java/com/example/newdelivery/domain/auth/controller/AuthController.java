package com.example.newdelivery.domain.auth.controller;

import com.example.newdelivery.domain.auth.dto.request.SignInRequest;
import com.example.newdelivery.domain.auth.dto.request.SignUpRequest;
import com.example.newdelivery.domain.auth.dto.response.SigninResponse;
import com.example.newdelivery.domain.auth.dto.response.SignupResponse;
import com.example.newdelivery.domain.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignUpRequest signupRequest) {

        SignupResponse response = authService.signup(signupRequest);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<SigninResponse> signin(@RequestBody SignInRequest signinRequest) {

        SigninResponse response = authService.signin(signinRequest);

        return ResponseEntity.ok(response);
    }
}
