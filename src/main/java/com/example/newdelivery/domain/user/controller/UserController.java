package com.example.newdelivery.domain.user.controller;



import com.example.newdelivery.common.security.CustomUserPrincipal;
import com.example.newdelivery.domain.user.dto.request.SignUpRequestDto;
import com.example.newdelivery.domain.user.dto.request.UpdateAddressRequestDto;
import com.example.newdelivery.domain.user.dto.request.UpdatePasswordRequestDto;
import com.example.newdelivery.domain.user.dto.response.SignUpResponseDto;
import com.example.newdelivery.domain.user.dto.response.UserResponseDto;
import com.example.newdelivery.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")

public class UserController {

    private final UserService userService;

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

    @PatchMapping("/password")
    public ResponseEntity<Void> updatePassWord (@AuthenticationPrincipal CustomUserPrincipal user, @RequestBody UpdatePasswordRequestDto requestDto){

        userService.updatePassword(user.getId(), requestDto.getOldPassword(), requestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/address")
    public ResponseEntity<UserResponseDto> updateAddress (@AuthenticationPrincipal CustomUserPrincipal user, @RequestBody UpdateAddressRequestDto requestDto){

        UserResponseDto userResponseDto = userService.updateAddress(user.getId(), requestDto);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<UserResponseDto> findUser (@AuthenticationPrincipal CustomUserPrincipal user){

        UserResponseDto responseDto = userService.findUser(user.getId());

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal CustomUserPrincipal user) {

        userService.deleteUser(user.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

