package com.example.programmers_auction.user.domain;

import com.example.programmers_auction.user.presentation.IllegalUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.example.programmers_auction.common.fixture.UserFixture.displayName1;
import static com.example.programmers_auction.common.fixture.UserFixture.userEmail1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {

    @Test
    @DisplayName("유저 생성")
    void create_user() {
        User user = new User(userEmail1, displayName1);
        assertThat(user).isNotNull();
        assertThat(user.getVerifyCode()).isNotBlank();
    }

    @ParameterizedTest
    @DisplayName("잘못된 이메일 양식이면 예외 발생")
    @ValueSource(strings = {"test&naver.com", "te**!!st&naver.com", "test.com", "testnavmercom"})
    void input_wrong_email_type(final String email) {
        assertThatThrownBy(() -> new User(email, displayName1))
                .isInstanceOf(IllegalUserException.class)
                .hasMessage("올바른 이메일 형식이 아닙니다.");
    }

    @ParameterizedTest
    @DisplayName("잘못된 닉네임 양식이면 예외 발생")
    @ValueSource(strings = {"@$%^", "qwereqwrweqrewqrewrewqrewqr", "q", "테스트test*&"})
    void input_wrong_username_type(final String displayName) {
        assertThatThrownBy(() -> new User(userEmail1, displayName))
                .isInstanceOf(IllegalUserException.class)
                .hasMessage("올바른 닉네임 형식이 아닙니다.");
    }

//    @Test
//    @DisplayName("프로필 설정")
//    void set_profile() {
//        //given
//        User test = new User("test@example.com", "test");
//        Profile profile = new Profile(Set.of(new Position("백엔드")));
//
//        //when
//        test.setProfile(profile);
//
//        //then
//        assertThat(test.getProfile()).isEqualTo(profile);
////        assertThat(test.getProfile().getPosition()).isEqualTo("백엔드");
//    }

}