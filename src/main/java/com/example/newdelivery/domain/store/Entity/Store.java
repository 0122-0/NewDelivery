package com.example.newdelivery.domain.store.Entity;

import com.example.newdelivery.common.baseEntity.BaseEntity;
import com.example.newdelivery.domain.store.Dto.StoreRequestDto;
import com.example.newdelivery.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor

public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private LocalTime openTime;

    @Column(nullable = false)
    private LocalTime closeTime;

    @Column(nullable = false)
    private Long minimumOrder;

    // 한명의 사장이
    @OneToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "store")
    private List<Menu> menus = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Store(Long id, String name, String content, String address, LocalTime openTime, LocalTime closeTime, Long minimumOrder, User user) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.minimumOrder = minimumOrder;
        this.user = user;
    }

    public Store(String name, String content, String address, LocalTime openTime, LocalTime closeTime, Long minimumOrder, User user) {
        this.name = name;
        this.content = content;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.minimumOrder = minimumOrder;
        this.user = user;
    }

    public Store(StoreRequestDto storeRequestDto) {
        this.name = storeRequestDto.getName();
        this.content = storeRequestDto.getContent();
        this.address = storeRequestDto.getAddress();
        this.openTime = storeRequestDto.getOpenTime();
        this.closeTime = storeRequestDto.getCloseTime();
        this.minimumOrder = storeRequestDto.getMinimumOrder();
    }

    public void update(StoreRequestDto storeRequestDto) {
        this.name = storeRequestDto.getName();
        this.content = storeRequestDto.getContent();
        this.address = storeRequestDto.getAddress();
        this.openTime = storeRequestDto.getOpenTime();
        this.closeTime = storeRequestDto.getCloseTime();
        this.minimumOrder = storeRequestDto.getMinimumOrder();
    }
}


