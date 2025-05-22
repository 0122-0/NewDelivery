package com.example.newdelivery.domain.menu.controller;


import com.example.newdelivery.common.security.CustomUserPrincipal;
import com.example.newdelivery.domain.menu.dto.request.MenuRequestDto;
import com.example.newdelivery.domain.menu.dto.response.MenuResponseDto;
import com.example.newdelivery.domain.menu.dto.request.MenuUpdateRequestDto;
import com.example.newdelivery.domain.menu.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/stores/{storeId}")
@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/menus")
    public ResponseEntity<MenuResponseDto> save(
            @AuthenticationPrincipal CustomUserPrincipal user,
            @PathVariable Long storeId,
            @RequestBody @Valid MenuRequestDto requestDto
    ) {
        MenuResponseDto menuResponseDto = menuService.save(user.getId(), storeId, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(menuResponseDto);

    }

    @GetMapping("/menus")
    public ResponseEntity<List<MenuResponseDto>> findAll(
            @AuthenticationPrincipal CustomUserPrincipal user,
            @PathVariable Long storeId
    ) {
        List<MenuResponseDto> menuList = menuService.findByStore(user.getId(), storeId);
        return ResponseEntity.ok(menuList);
    }

    @GetMapping("/menus/{menuId}")
    public ResponseEntity<MenuResponseDto> findOne(
            @AuthenticationPrincipal CustomUserPrincipal user,
            @PathVariable Long menuId,
            @PathVariable Long storeId
    ) {
        MenuResponseDto findMenu = menuService.findById(user.getId(), menuId, storeId);
        return ResponseEntity.ok(findMenu);
    }

    @PutMapping("/menus/{menuId}")
    public ResponseEntity<MenuResponseDto> update(
            @AuthenticationPrincipal CustomUserPrincipal user,
            @PathVariable Long menuId,
            @RequestBody MenuUpdateRequestDto requestDto,
            @PathVariable Long storeId
    ) {
        MenuResponseDto updated = menuService.update(user.getId(), menuId,requestDto, storeId);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/menus/{menuId}")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal CustomUserPrincipal user,
            @PathVariable Long menuId,
            @PathVariable Long storeId
    ) {
        menuService.delete(user.getId(), menuId, storeId);
        return ResponseEntity.noContent().build(); //204 No Content
    }
}
