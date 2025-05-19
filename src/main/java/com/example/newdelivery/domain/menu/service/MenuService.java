package com.example.newdelivery.domain.menu.service;

import com.example.newdelivery.domain.menu.dto.request.MenuRequestDto;
import com.example.newdelivery.domain.menu.dto.response.MenuResponseDto;
import com.example.newdelivery.domain.menu.dto.request.MenuUpdateRequestDto;
import com.example.newdelivery.domain.menu.entity.Menu;
import com.example.newdelivery.domain.menu.repository.MenuRepositoy;
import com.example.newdelivery.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepositoy menuRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    //메뉴 생성
    @Transactional
    public MenuResponseDto save(Long storeId, MenuRequestDto requestDto) {
        Store store = storeRepository.findByIdOrElseThrow(storeId);
        Menu menu = new Menu(store, requestDto.getName(), requestDto.getContent(), requestDto.getPrice());
        Menu saved = menuRepository.save(menu);
        return MenuResponseDto.toDto(saved);
    }
    //메뉴 가게별 전체 조회
    @Transactional
    public List<MenuResponseDto> findByStore(Long storeId) {
        List<MenuResponseDto> menus = menuRepository.findByStore(storeId)
                .stream()
                .map(MenuResponseDto::toDto)
                .toList();
        if (menus.isEmpty()) {
            return Collections.emptyList();
        }
        return menus;
    }
    //메뉴 단건 조회
    @Transactional
    public MenuResponseDto findById(Long menuId) {
        Menu menu = menuRepository.findByIdOrElseThrow(menuId);
        return MenuResponseDto.toDto(menu);
    }
    //메뉴 수정
    @Transactional
    public MenuResponseDto update(Long menuId, MenuUpdateRequestDto requestDto) {
        Menu menu = menuRepository.findByIdOrElseThrow(menuId);
        menu.update(requestDto.getName(), requestDto.getContent(), requestDto.getPrice());
        return MenuResponseDto.toDto(menu);
    }
    //메뉴 삭제
    @Transactional
    public void delete(Long menuId) {
        Menu menu = menuRepository.findByIdOrElseThrow(menuId);
        menuRepository.delete(menu);
    }
}
