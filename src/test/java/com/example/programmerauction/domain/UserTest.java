package com.example.programmerauction.domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {

    @Test
    @DisplayName("잘못된 이메일 양식이면 예외 발생")
    void input_wrong_email_type() {
        //given
        final String email1 = "test&naver.com";
        final String email2 = "te**!!st&naver.com";
        final String email3 = "test.com";
        final String email4 = "testnavmercom";
        final String userName = "test";

        //when & then
        assertThatThrownBy(() -> new User(email1, userName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 이메일 형식이 아닙니다.");

        assertThatThrownBy(() -> new User(email2, userName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 이메일 형식이 아닙니다.");

        assertThatThrownBy(() -> new User(email3, userName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 이메일 형식이 아닙니다.");

        assertThatThrownBy(() -> new User(email4, userName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 이메일 형식이 아닙니다.");
    }

    @Test
    @DisplayName("잘못된 닉네임 양식이면 예외 발생")
    void input_wrong_username_type() {
        //given
        final String email = "test@example.com";
        final String userName1 = "@$%^";
        final String userName2 = "qwereqwrweqrewqrewrewqrewqr";
        final String userName3 = "q";
        final String userName4 = "테스트test*&";

        //when & then
        assertThatThrownBy(() -> new User(email, userName1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 닉네임 형식이 아닙니다.");

        assertThatThrownBy(() -> new User(email, userName2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 닉네임 형식이 아닙니다.");

        assertThatThrownBy(() -> new User(email, userName3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 닉네임 형식이 아닙니다.");

        assertThatThrownBy(() -> new User(email, userName4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 닉네임 형식이 아닙니다.");

    }

    @Test
    @DisplayName("올바른 이메일, 닉네임")
    void correct_email_username() {
        //given
        final String email = "test@example.com";
        final String userName = "test예13";

        //when
        User user = new User(email, userName);

        //then
        assertThat(user.getEmail()).isEqualTo("test@example.com");
        assertThat(user.getName()).isEqualTo("test예13");
        assertThat(user.isCertified()).isFalse();

    }

}