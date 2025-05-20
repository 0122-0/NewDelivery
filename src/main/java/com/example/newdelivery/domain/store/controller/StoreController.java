package com.example.newdelivery.domain.store.controller;

import com.example.newdelivery.domain.store.Dto.StoreRequestDto;
import com.example.newdelivery.domain.store.Dto.StoreResponseDto;
import com.example.newdelivery.domain.store.Entity.Store;
import com.example.newdelivery.domain.store.Repository.StoreRepository;
import com.example.newdelivery.domain.store.Service.StoreService;
import com.example.newdelivery.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
@Slf4j

public class StoreController {
    private final StoreService storeService;


    @PostMapping
    public ResponseEntity<StoreResponseDto> createStore(@RequestBody @Validated StoreRequestDto storeRequestDto) {
        StoreResponseDto responseDto = storeService.createStoreService(storeRequestDto);
        return new ResponseEntity<>(HttpStatusCode.valueOf(201));
    }

    @PutMapping("/{storeId}")
    public ResponseEntity<StoreResponseDto> updateStore(@RequestBody @Validated StoreRequestDto storeRequestDto,
                                                       @PathVariable Long storeId) {
        StoreRequestDto storeResponseDto = storeService.updateStoreService(storeId, storeRequestDto);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<String> deleteStore(@PathVariable Long storeId) throws IllegalAccessException{
    storeService.deleteStoreService(storeId);
    return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping
    public ResponseEntity<List<Store>> findAllStore() {
        List<Store> storeList = storeService.checkAllStoreService();
        return new ResponseEntity<>(storeList, HttpStatusCode.valueOf(200));

    }

    @GetMapping("/storeId")
    public ResponseEntity<Long> findById(@PathVariable Long storeId)throws IllegalAccessException {
            storeService.checkOneStoreService(storeId);

        return new ResponseEntity<>(storeId, HttpStatusCode.valueOf(200));



        }



}
