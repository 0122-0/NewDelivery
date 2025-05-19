package com.example.newdelivery.domain.user.service;

import com.example.newdelivery.domain.user.dto.request.UpdatePasswordRequestDto;
import com.example.newdelivery.domain.user.dto.response.SignUpResponseDto;
import com.example.newdelivery.domain.user.entity.User;
import com.example.newdelivery.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public SignUpResponseDto signUp(String email, String password, String name, String nickname, String phone, String address, String role){

        User users = new User(email, password, name, nickname, phone, address, role);
        User savedUser = userRepository.save(users);

        return new SignUpResponseDto(savedUser.getEmail(), savedUser.getPassword(),savedUser.getName(), savedUser.getNickName(), savedUser.getPhone(), savedUser.getAddress(), savedUser.getRole().name(), savedUser.getCreatedAt());
    }

    @Transactional
    public void updatePassword (Long id, String oldPassword, String newPassword) {

        User findUserById = userRepository.findByIdOrElseThrow(id);

        if(!findUserById.getPassword().equals(oldPassword)) {
            throw new IllegalArgumentException("기존 비밀 번호가 일치 하지 않습니다.");
        }

        findUserById.updatePassword(newPassword);
    }
}
