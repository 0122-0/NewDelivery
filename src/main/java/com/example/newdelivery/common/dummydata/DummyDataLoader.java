package com.example.newdelivery.common.dummydata;

import com.example.newdelivery.domain.menu.entity.Menu;
import com.example.newdelivery.domain.menu.repository.MenuRepository;
import com.example.newdelivery.domain.review.entity.Review;
import com.example.newdelivery.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DummyDataLoader implements CommandLineRunner {

    private final MenuRepository menuRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public void run(String... args) {
        List<Menu> menus = menuRepository.findAll(); // DB에 있는 메뉴들

        if (menus.isEmpty()) {
            System.out.println("더미 리뷰 생성을 위해 먼저 메뉴 데이터를 생성하세요.");
            return;
        }

        List<Review> reviews = new ArrayList<>();

        for (Menu menu : menus) {
            for (int i = 1; i <= 50000; i++) {
                String content = "이 치킨 후라이드는 정말 맛없어요! 리뷰 #" + i + " (" + menu.getName() + ")";

                reviews.add(Review.builder()
                        .content(content)
                        .rating(5)
                        .menu(menu)
                        .store(menu.getStore())
                        .build());
            }
        }

        reviewRepository.saveAll(reviews);
        System.out.println("리뷰 더미 데이터 생성 완료 (" + reviews.size() + "건)");
    }
}
