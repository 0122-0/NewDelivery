package com.example.newdelivery.domain.auth.service;

import com.example.newdelivery.common.jwt.JwtUtil;
import com.example.newdelivery.domain.auth.dto.request.SignInRequest;
import com.example.newdelivery.domain.auth.dto.request.SignUpRequest;
import com.example.newdelivery.domain.auth.dto.response.SigninResponse;
import com.example.newdelivery.domain.auth.dto.response.SignupResponse;
import com.example.newdelivery.domain.user.entity.User;
import com.example.newdelivery.domain.user.enums.Role;
import com.example.newdelivery.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public SignupResponse signup(SignUpRequest signupRequest) {

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());

        User newUser = new User(
                signupRequest.getEmail(),
                encodedPassword,
                signupRequest.getName(),
                signupRequest.getNickName(),
                signupRequest.getPhone(),
                signupRequest.getAddress(),
                Role.of(signupRequest.getRole()).name()
        );

        User savedUser = userRepository.save(newUser);

        return new SignupResponse(savedUser.getEmail(),savedUser.getName(),savedUser.getNickName(),savedUser.getPhone(),savedUser.getAddress(),savedUser.getRole().name(),savedUser.getCreatedAt());
    }

    @Transactional(readOnly = true)
    public SigninResponse signin(SignInRequest signinRequest) {
        User user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(
                () -> new RuntimeException("가입되지 않은 유저입니다."));

        // 로그인 시 이메일과 비밀번호가 일치하지 않을 경우 401을 반환합니다.
        if (!passwordEncoder.matches(signinRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("잘못된 비밀번호입니다.");
        }

        String bearerToken = jwtUtil.createToken(user.getId(), user.getEmail(), user.getRole());

        return new SigninResponse(bearerToken);
    }
}

