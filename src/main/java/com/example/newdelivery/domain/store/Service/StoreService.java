package com.example.newdelivery.domain.store.Service;


import com.example.newdelivery.domain.store.Dto.StoreRequestDto;
import com.example.newdelivery.domain.store.Dto.StoreResponseDto;
import com.example.newdelivery.domain.store.Entity.Store;
import com.example.newdelivery.domain.store.Repository.StoreRepository;
import com.example.newdelivery.domain.user.entity.User;
import com.example.newdelivery.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class StoreService {

    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    // 생성
    @Transactional
    public StoreResponseDto createStoreService(Long userId, StoreRequestDto storeRequestDto) {

        User owner = userRepository.findByIdOrElseThrow(userId);

        Store store = storeRequestDto.toEntity(owner);

        storeRepository.save(store);

        return StoreResponseDto.from(storeRepository.save(store));
    }

    // 수정
    @Transactional
    public StoreResponseDto updateStoreService(Long userId, Long storeId, StoreRequestDto storeRequestDto){

        User user = userRepository.findByIdOrElseThrow(userId);

        Store store = storeRepository.findById(storeId).orElseThrow();

        store.update(storeRequestDto);

        Store savedStore = storeRepository.save(store);

        return StoreResponseDto.from(savedStore);

    }
    // 삭제
    @Transactional
    public void deleteStoreService(Long userId, Long storeId) throws IllegalAccessException {

        User user = userRepository.findByIdOrElseThrow(userId);

        Store store = storeRepository.findById(storeId).orElseThrow(IllegalAccessException::new);
        storeRepository.delete(store);

    }

    // 전체 조회
    @Transactional
    public List<Store> checkAllStoreService(){
        return storeRepository.findAll();
    }

    // 단건 조회
    @Transactional
    public StoreResponseDto checkOneStoreService(Long userId, Long storeId) throws IllegalAccessException {

        User user = userRepository.findByIdOrElseThrow(userId);

        Store store = storeRepository.findById(storeId).orElseThrow(IllegalAccessException::new);

        return StoreResponseDto.from(store);
    }
}
