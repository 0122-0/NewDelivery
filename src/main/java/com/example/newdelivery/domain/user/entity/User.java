package com.example.newdelivery.domain.user.entity;

import com.example.newdelivery.common.baseEntity.BaseEntity;
import com.example.newdelivery.domain.user.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String nickName;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public User(String email, String password, String name, String nickName, String phone, String address, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickName = nickName;
        this.phone = phone;
        this.address = address;
        this.role = Role.valueOf(role);
    }

    public void updatePassword (String newPassword) {
        this.password = newPassword;
    }

    public void updateAddress (String newAddress) {
        this.address = newAddress;
    }

    //    // 한 명의 owner 가 여러 가게
//    @OneToMany(mappedBy = "owner")
//    private List<Store> stores = new ArrayList<>();
//
//    // 한 명의 유저가 여러가지 리뷰를 남김
//    @OneToMany(mappedBy = "user")
//    private List<Review> reviews = new ArrayList<>();
//
//    // 한 명의 유저가 여러 주문 가능
//    @OneToMany(mappedBy = "user")
//    private List<Order> orders = new ArrayList<>();


}