package com.example.newdelivery.domain.search.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SearchKeyword {

    @Id
    private String keyword;

    private int count = 1;

    public SearchKeyword(String keyword) {
        this.keyword = keyword;
        this.count = 1;
    }

    public void increaseCount() {
        this.count++;
    }
}
