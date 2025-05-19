package com.example.newdelivery.domain.menu.controller;


import com.example.newdelivery.domain.menu.dto.MenuRequestDto;
import com.example.newdelivery.domain.menu.dto.MenuResponseDto;
import com.example.newdelivery.domain.menu.dto.MenuUpdateRequestDto;
import com.example.newdelivery.domain.menu.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/store/{storeId}/menus")
    public ResponseEntity<MenuResponseDto> save(
            @PathVariable Long storeId,
            @RequestBody @Valid MenuRequestDto requestDto
    ) {
        MenuResponseDto menuResponseDto = menuService.save(storeId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuResponseDto);
    }

    @GetMapping("/store/{storeId}/menus")
    public ResponseEntity<List<MenuResponseDto>> findByStore(
            @PathVariable Long storeId
    ) {
        List<MenuResponseDto> menuList = menuService.findByStore(storeId);
        return ResponseEntity.ok(menuList);
    }

    @GetMapping("/menus/{menuId}")
    public ResponseEntity<MenuResponseDto> findOne(
            @PathVariable Long menuId
    ) {
        MenuResponseDto findMenu = menuService.findById(menuId);
        return ResponseEntity.ok(findMenu);
    }

    @PutMapping("/menus/{menuId}")
    public ResponseEntity<MenuResponseDto> update(
            @PathVariable Long menuId,
            @RequestBody MenuUpdateRequestDto requestDto
    ) {
        MenuResponseDto updated = menuService.update(menuId,requestDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/menus/{menuId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long menuId
    ) {
        menuService.delete(menuId);
        return ResponseEntity.noContent().build(); //204 No Content
    }
}
