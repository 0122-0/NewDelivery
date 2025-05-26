package com.example.newdelivery.domain.store.Repository;

import com.example.newdelivery.domain.store.Entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface StoreRepository extends JpaRepository<Store, Long> {

    default Store findStoreByIdOrElseThrow(Long storeId){
        return findById(storeId).orElseThrow(()->new RuntimeException("가게를 찾을 수 없습니다."));
    }

}
