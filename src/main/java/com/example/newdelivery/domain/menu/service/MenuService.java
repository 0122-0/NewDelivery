package com.example.newdelivery.domain.menu.service;

import com.example.newdelivery.domain.menu.dto.request.MenuRequestDto;
import com.example.newdelivery.domain.menu.dto.response.MenuResponseDto;
import com.example.newdelivery.domain.menu.dto.request.MenuUpdateRequestDto;
import com.example.newdelivery.domain.menu.entity.Menu;
import com.example.newdelivery.domain.menu.repository.MenuRepository;
import com.example.newdelivery.domain.store.Entity.Store;
import com.example.newdelivery.domain.store.Repository.StoreRepository;
import com.example.newdelivery.domain.user.entity.User;
import com.example.newdelivery.domain.user.repository.UserRepository;
import com.example.newdelivery.global.exception.ApiException;
import com.example.newdelivery.global.exception.ErrorType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    //메뉴 생성
    @Transactional
    public MenuResponseDto save(Long userId, Long storeId, MenuRequestDto requestDto) {

        User user = userRepository.findByIdOrElseThrow(userId);

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND
                        , ErrorType.STORE_NOT_FOUND
                        , "존재하지 않는 가게입니다."));
        Menu menu = new Menu(store, requestDto.getName(), requestDto.getContent(), requestDto.getPrice());
        Menu saved = menuRepository.save(menu);
        return MenuResponseDto.toDto(saved);
    }

    //메뉴 가게별 전체 조회
    @Transactional
    public List<MenuResponseDto> findByStore(Long userId, Long storeId) {

        User user = userRepository.findByIdOrElseThrow(userId);

        Store store = storeRepository.findStoreByIdOrElseThrow(storeId);

        List<Menu> menus = menuRepository.findAll();

        if (menus.isEmpty()) {
            throw new ApiException(HttpStatus.NOT_FOUND
                    , ErrorType.MENU_NOT_FOUND
                    , "해당 가게의 메뉴가 존재하지 않습니다");
        }
        return menus.stream().map(menu->MenuResponseDto.toDto(menu)).collect(Collectors.toList());
    }

    //메뉴 단건 조회
    @Transactional
    public MenuResponseDto findById(Long userId, Long menuId,Long storeId) {

        User user = userRepository.findByIdOrElseThrow(userId);

        Store store = storeRepository.findStoreByIdOrElseThrow(storeId);

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND
                        , ErrorType.MENU_NOT_FOUND
                        , "메뉴가 존재하지 않습니다."));
        return MenuResponseDto.toDto(menu);
    }

    //메뉴 수정
    @Transactional
    public MenuResponseDto update(Long userId, Long menuId, MenuUpdateRequestDto requestDto,Long storeId) {

        User user = userRepository.findByIdOrElseThrow(userId);

        Store store = storeRepository.findStoreByIdOrElseThrow(storeId);

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND
                        , ErrorType.MENU_NOT_FOUND
                        , "메뉴가 존재하지 않습니다."));
        menu.update(requestDto.getName(), requestDto.getContent(), requestDto.getPrice());
        return MenuResponseDto.toDto(menu);
    }

    //메뉴 삭제
    @Transactional
    public void delete(Long userId, Long menuId,Long storeId) {

        User user = userRepository.findByIdOrElseThrow(userId);

        Store store = storeRepository.findStoreByIdOrElseThrow(storeId);

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND
                        , ErrorType.MENU_NOT_FOUND
                        , "메뉴가 존재하지 않습니다."));
        menuRepository.delete(menu);
    }
}
