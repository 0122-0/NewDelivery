package com.example.newdelivery.domain.store.Entity;

import com.example.newdelivery.common.baseEntity.BaseEntity;
import com.example.newdelivery.domain.menu.entity.Menu;
import com.example.newdelivery.domain.review.entity.Review;
import com.example.newdelivery.domain.store.Dto.StoreRequestDto;
import com.example.newdelivery.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.time.LocalTime;
import java.util.ArrayList;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
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

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menus = new ArrayList<>();

//    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Review> reviews = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    private User owner;


    public Store(Long id, String name, String content, String address, LocalTime openTime, LocalTime closeTime, User owner) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.owner = owner;
    }

    public Store(String name, String content, String address, LocalTime openTime, LocalTime closeTime, User owner) {
        this.name = name;
        this.content = content;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.owner = owner;
    }

    public Store(StoreRequestDto storeRequestDto) {
        this.name = storeRequestDto.getName();
        this.content = storeRequestDto.getContent();
        this.address = storeRequestDto.getAddress();
        this.openTime = storeRequestDto.getOpenTime();
        this.closeTime = storeRequestDto.getCloseTime();
    }

    public void update(StoreRequestDto storeRequestDto) {
        this.name = storeRequestDto.getName();
        this.content = storeRequestDto.getContent();
        this.address = storeRequestDto.getAddress();
        this.openTime = storeRequestDto.getOpenTime();
        this.closeTime = storeRequestDto.getCloseTime();
    }
}


