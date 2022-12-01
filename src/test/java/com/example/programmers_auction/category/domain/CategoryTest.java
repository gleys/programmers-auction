package com.example.programmers_auction.category.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CategoryTest {

    @Test
    @DisplayName("카테고리 생성")
    void create_category() {
        Category parent = new Category("IT");
        Category child = new Category("앱");

        parent.addChild(child);

        Assertions.assertThat(parent.getName()).isEqualTo("IT");
        Assertions.assertThat(child.getName()).isEqualTo("앱");
        Assertions.assertThat(parent.getChildrenCategories().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("하위 카테고리 삭제")
    void delete_child_category() {
        Category parent = new Category("IT");
        Category child = new Category("앱");

        parent.addChild(child);
        parent.deleteChild(child);

        Assertions.assertThat(parent.getChildrenCategories().size()).isEqualTo(0);
    }


}