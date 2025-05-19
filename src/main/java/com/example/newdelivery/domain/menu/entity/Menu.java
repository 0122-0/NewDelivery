package com.example.newdelivery.domain.menu.entity;

import com.example.newdelivery.common.baseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "menu")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer price;

    public Menu(Store store, String name, String content, Integer price) {
        this.store = store;
        this.name = name;
        this.content = content;
        this.price = price;
    }

    public void update(String newName, String newContent, Integer newPrice) {
        this.name = newName;
        this.content = newContent;
        this.price = newPrice;
    }


}
