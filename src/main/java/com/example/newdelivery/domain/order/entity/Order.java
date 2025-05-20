package com.example.newdelivery.domain.order.entity;

import com.example.newdelivery.common.baseEntity.BaseEntity;
import com.example.newdelivery.domain.menu.entity.Menu;
import com.example.newdelivery.domain.order.enums.OrderStatus;
import com.example.newdelivery.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order extends BaseEntity {

    // 오더 Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 유저 Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 가게 Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    // 메뉴 Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    // 메뉴 개수
    @Column(nullable = false)
    private int quantity;

    // 총 가격
    @Column(nullable = false)
    private int totalPrice;

    // 주문 상태
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderstatus;

    @Builder
    public Order(User user, Store store, Menu menu, int quantity, int totalPrice, OrderStatus orderstatus) {
        this.user = user;
        this.store = store;
        this.menu = menu;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderstatus = OrderStatus.PENDING; // 기본값 설정
    }
}
