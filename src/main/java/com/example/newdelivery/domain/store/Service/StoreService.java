package com.example.newdelivery.domain.store.Service;


import com.example.newdelivery.domain.store.Dto.StoreRequestDto;
import com.example.newdelivery.domain.store.Dto.StoreResponseDto;
import com.example.newdelivery.domain.store.Entity.Store;
import com.example.newdelivery.domain.store.Repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class StoreService {

    private final StoreRepository storeRepository;
    // 생성
    @Transactional
    public StoreResponseDto createStoreService(StoreRequestDto storeRequestDto) {
        Store store = new Store(storeRequestDto);
        storeRepository.save(store);
        return null;
    }

    // 수정
    @Transactional
    public StoreRequestDto updateStoreService(Long storeId, StoreRequestDto storeRequestDto){
        Store store = storeRepository.findById(storeId).orElseThrow();
        store.update(storeRequestDto);
        storeRepository.save(store);

        return new StoreRequestDto(store);
    }

    // 삭제
    @Transactional
    public void deleteStoreService(Long storeId) throws IllegalAccessException {
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
    public StoreRequestDto checkOneStoreService(Long storeId) throws IllegalAccessException {
        Store store = storeRepository.findById(storeId).orElseThrow(IllegalAccessException::new);

        return new StoreRequestDto(store);
    }
}
