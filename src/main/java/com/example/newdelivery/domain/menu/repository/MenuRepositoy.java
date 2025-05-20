package com.example.newdelivery.domain.menu.repository;

import com.example.newdelivery.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface MenuRepositoy extends JpaRepository<Menu,Long> {

    List<Menu> findByStore(Long storeId);
}
