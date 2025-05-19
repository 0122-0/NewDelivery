package com.example.newdelivery.repository;

import com.example.newdelivery.domain.store.Entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface StoreRepository extends JpaRepository<Store, Long> {
}
