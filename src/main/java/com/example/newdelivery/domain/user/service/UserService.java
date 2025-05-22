package com.example.newdelivery.domain.user.service;

import com.example.newdelivery.domain.user.dto.request.UpdateAddressRequestDto;
import com.example.newdelivery.domain.user.dto.response.SignUpResponseDto;
import com.example.newdelivery.domain.user.dto.response.UserResponseDto;
import com.example.newdelivery.domain.user.entity.User;
import com.example.newdelivery.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public SignUpResponseDto signUp(String email, String password, String name, String nickname, String phone, String address, String role){

        User users = new User(email, password, name, nickname, phone, address, role);
        User savedUser = userRepository.save(users);

        return new SignUpResponseDto(savedUser.getEmail(), savedUser.getPassword(),savedUser.getName(), savedUser.getNickName(), savedUser.getPhone(), savedUser.getAddress(), savedUser.getRole().name(), savedUser.getCreatedAt());
    }

    @Transactional
    public void updatePassword (Long id, String oldPassword, String newPassword) {

        User findUserById = userRepository.findByIdOrElseThrow(id);

        if(!passwordEncoder.matches(oldPassword, findUserById.getPassword())){
            throw new RuntimeException("에");
        }

        if(passwordEncoder.matches(newPassword, findUserById.getPassword())){
            throw new RuntimeException("에");
        }

        String encodedPassword = passwordEncoder.encode(newPassword);

        findUserById.updatePassword(encodedPassword);
    }

    @Transactional
    public UserResponseDto updateAddress (Long id, UpdateAddressRequestDto requestDto) {

        User findUserById = userRepository.findByIdOrElseThrow(id);

        findUserById.updateAddress(requestDto.getAddress());

        return new UserResponseDto(findUserById.getEmail(), findUserById.getName(),findUserById.getNickName(),findUserById.getPhone(),findUserById.getAddress(),findUserById.getRole().name(),findUserById.getCreatedAt(),findUserById.getUpdatedAt());
    }

    @Transactional
    public UserResponseDto findUser (Long id) {

        User findUserById = userRepository.findByIdOrElseThrow(id);
        return new UserResponseDto(findUserById.getEmail(),findUserById.getName(),findUserById.getNickName(),findUserById.getPhone(),findUserById.getAddress(),findUserById.getRole().name(),findUserById.getCreatedAt(),findUserById.getUpdatedAt());

    }

    @Transactional
    public void deleteUser (Long id) {

        User findUserById = userRepository.findByIdOrElseThrow(id);

        userRepository.delete(findUserById);
    }
}
