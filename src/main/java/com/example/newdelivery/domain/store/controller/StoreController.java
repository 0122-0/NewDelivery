package com.example.newdelivery.domain.store.controller;

import com.example.newdelivery.common.security.CustomUserPrincipal;
import com.example.newdelivery.domain.store.Dto.StoreRequestDto;
import com.example.newdelivery.domain.store.Dto.StoreResponseDto;
import com.example.newdelivery.domain.store.Entity.Store;
import com.example.newdelivery.domain.store.Repository.StoreRepository;
import com.example.newdelivery.domain.store.Service.StoreService;
import com.example.newdelivery.domain.user.entity.User;
import com.example.newdelivery.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
@Slf4j

public class StoreController {
    private final StoreService storeService;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    // 가게생성
    @PostMapping
    public ResponseEntity<StoreResponseDto> createStore(@AuthenticationPrincipal CustomUserPrincipal user, @RequestBody @Validated StoreRequestDto storeRequestDto) {

        StoreResponseDto responseDto = storeService.createStoreService(user.getId(), storeRequestDto);

        return new ResponseEntity<>(responseDto, HttpStatusCode.valueOf(201));
    }

    //
    @PutMapping("/{storeId}")
    public ResponseEntity<StoreResponseDto> updateStore(
            @AuthenticationPrincipal CustomUserPrincipal user,
            @RequestBody @Validated StoreRequestDto storeRequestDto,
                                                       @PathVariable Long storeId) {
        StoreResponseDto storeResponseDto = storeService.updateStoreService(user.getId(),storeId, storeRequestDto);
        return new ResponseEntity<>(storeResponseDto, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<String> deleteStore(@AuthenticationPrincipal CustomUserPrincipal user,@PathVariable Long storeId) throws IllegalAccessException{
    storeService.deleteStoreService(user.getId(), storeId);
    return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public List<StoreResponseDto> getStores() {
        List<Store> stores = storeRepository.findAll();
        return stores.stream()
                .map(StoreResponseDto::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponseDto> findById(@AuthenticationPrincipal CustomUserPrincipal user, @PathVariable Long storeId)throws IllegalAccessException {

        StoreResponseDto storeResponseDto = storeService.checkOneStoreService(user.getId(), storeId);

        return new ResponseEntity<>(storeResponseDto, HttpStatusCode.valueOf(200));
    }



}
