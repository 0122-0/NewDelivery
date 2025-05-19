package com.example.newdelivery.domain.user.controller;

import com.example.newdelivery.domain.user.dto.request.SignUpRequestDto;
import com.example.newdelivery.domain.user.dto.request.UpdateAddressRequestDto;
import com.example.newdelivery.domain.user.dto.request.UpdatePasswordRequestDto;
import com.example.newdelivery.domain.user.dto.response.SignUpResponseDto;
import com.example.newdelivery.domain.user.dto.response.UserResponseDto;
import com.example.newdelivery.domain.user.entity.User;
import com.example.newdelivery.domain.user.repository.UserRepository;
import com.example.newdelivery.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    //회원 가입
    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponseDto> signUp (@RequestBody SignUpRequestDto requestDto){

        SignUpResponseDto SignUpResponseDto = userService.signUp(
                requestDto.getEmail(),
                requestDto.getPassword(),
                requestDto.getName(),
                requestDto.getNickName(),
                requestDto.getPhone(),
                requestDto.getAddress(),
                requestDto.getRole()
        );

        return new ResponseEntity<>(SignUpResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Void> updatePassWord (@PathVariable Long id, @RequestBody UpdatePasswordRequestDto requestDto){

        userService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/address")
    public ResponseEntity<UserResponseDto> updateAddress (@PathVariable Long id, @RequestBody UpdateAddressRequestDto requestDto){

        UserResponseDto userResponseDto = userService.updateAddress(id, requestDto);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUser (@PathVariable Long id){

        UserResponseDto responseDto = userService.findUser(id);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}