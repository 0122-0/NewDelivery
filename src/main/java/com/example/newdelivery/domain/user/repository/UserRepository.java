package com.example.newdelivery.domain.user.repository;

import com.example.newdelivery.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById (Long userId);

    Optional<User> findByEmail (String email);

    boolean existsByEmail (String email);

    default User findByIdOrElseThrow (Long userId) {
        return  findUserById(userId).orElseThrow(() -> new RuntimeException("존재하지 않은 ID입니다."));
    }

}