package com.example.newdelivery.domain.menu.repository;

import com.example.newdelivery.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Long> {

}
