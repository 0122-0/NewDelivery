package com.example.newdelivery.domain.order.service;

import com.example.newdelivery.domain.menu.entity.Menu;
import com.example.newdelivery.domain.menu.repository.MenuRepositoy;
import com.example.newdelivery.domain.order.dto.request.OrderCreateRequest;
import com.example.newdelivery.domain.order.dto.response.OrderResponseDto;
import com.example.newdelivery.domain.order.dto.response.OrderStatusResponseDto;
import com.example.newdelivery.domain.order.entity.Order;
import com.example.newdelivery.domain.order.enums.OrderStatus;
import com.example.newdelivery.domain.order.repository.OrderRepository;
import com.example.newdelivery.domain.user.entity.User;
import com.example.newdelivery.domain.user.repository.UserRepository;
import com.example.newdelivery.global.exception.ApiException;
import com.example.newdelivery.global.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.hibernate.usertype.UserType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final MenuRepositoy menuRepository;

    // 사용자 주문 생성
    @Transactional
    public OrderResponseDto createOrder(Long userId, OrderCreateRequest dto) {

        User user  = userRepository.findByIdOrElseThrow(userId);

        // 가게 검증
        Store store = storeRepository.findById(dto.getStoreId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorType.INVALID_PARAMETER, "가게를 찾을 수 없습니다."));

        // 메뉴 검증
        Menu menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorType.INVALID_PARAMETER, "메뉴를 찾을 수 없습니다."));

        List<MenuOptions> menuOptions = menuOptionsRepository.findByMenuId(menu.getId());

         // 가게 영업시간에만 주문 가능
        LocalTime now = LocalTime.now();
        if (now.isBefore(store.getOpeningTime()) || now.isAfter(store.getClosingTime())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorType.INVALID_PARAMETER, "영업시간이 아닙니다.");
        }

        // 총 가격
        int totalOptionPrice = menu.stream()
                .mapToInt(Menu::getPrice)
                .sum();

        int totalPrice = (menu.getPrice() + totalOptionPrice) * dto.getQuantity();

        // 가게에서 설정한 최소 주문 금액을 만족해야 주문이 가능
        if (totalPrice < store.getMinOrderPrice()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorType.INVALID_PARAMETER, String.format("최소 주문 금액은 %d원입니다.", store.getMinOrderPrice()));
        }

        // 주문 설정
        Order order = Order.builder()
                .user(user)
                .store(store)
                .menu(menu)
                .count(dto.getCount())
                .totalPrice(totalPrice)
                .orderStatus(OrderStatus.PENDING) // 기본값 설정
                .updatedAt(LocalDateTime.now())
                .build();

        Order savedOrder = orderRepository.save(order);

        return new OrderResponseDto(
                savedOrder.getId(),
                store.getId(),
                savedOrder.getMenu().getId(),
                savedOrder.getCount(),
                savedOrder.getTotalPrice(),
                savedOrder.getUpdatedAt()
        );
    }

    // 사용자 주문 취소
    @Transactional
    public OrderStatusResponseDto cancelOrder(Long userId, Long orderId) {
        Order order = getOrderById(orderId);

        validateUserOrder(order, userId);

        if (!OrderStatus.PENDING.equals(order.getOrderStatus())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorType.INVALID_PARAMETER,"조리 중인 주문은 취소할 수 없습니다.");
        }

        order.updateOrderStatus(OrderStatus.CANCELED);

        return OrderStatusResponseDto.from(order);
    }

    // 오너 주문 상태 변경
    @Transactional
    public OrderStatusResponseDto changeOrderStatus(UserType userType, Long orderId, OrderStatus newStatus ) {

        validateOwnerOrder(userType);

        Order order = getOrderById(orderId);

        if (!OrderStatus.PENDING.equals(order.getOrderStatus())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorType.INVALID_PARAMETER,"변경할 수 없는 주문입니다.");
        }

        if (!isAllowedChangeStatus(newStatus)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorType.INVALID_PARAMETER, "허용되지 않은 주문 상태입니다.");
        }

        order.updateOrderStatus(newStatus);
        return OrderStatusResponseDto.from(order);
    }

    private void validateUserOrder(Order order, Long userId) {
        if (!ObjectUtils.nullSafeEquals(userId, order.getUser().getId())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorType.INVALID_PARAMETER, "주문한 유저가 아닙니다.");
        }
    }

    private void validateOwnerOrder(UserType userType) {
        if (!UserType.OWNER.equals(userType)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, ErrorType.INVALID_PARAMETER, "오너만 주문 상태를 변경할 수 있습니다.");
        }
    }

    private boolean isAllowedChangeStatus(OrderStatus status) {
        return status == OrderStatus.ACCEPTED || status == OrderStatus.REJECTED;
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorType.NO_RESOURCE,
                        "주문을 찾을 수 없습니다."));
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorType.NO_RESOURCE, "존재하지 않는 주문입니다."));
    }
}
