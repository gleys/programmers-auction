package com.example.programmers_auction.profile.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.example.programmers_auction.common.fixture.ProfileFixture.testProfile1;
import static com.example.programmers_auction.common.fixture.UserFixture.user1;
import static com.example.programmers_auction.common.fixture.UserPositionFixture.userPosition1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ProfileTest {

    @Test
    @DisplayName("올바른 url 양식이 아니면 예외 발생")
    void input_wrong_url_type() {
        //given
        final String url1 = "https://github,com/";
        final String url2 = "htttp://github.com/";
        final String url3 = "http:/github.com/";
        final String url4 = "https://github";
        final String url5 = "https://www,github,0om/";

        //when & then
        Profile profile = new Profile(user1);

        assertThatThrownBy(() -> profile.changeImageUrl(url1))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("올바른 형식의 url 형식이 아닙니다.");

        assertThatThrownBy(() -> profile.changeImageUrl(url2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 형식의 url 형식이 아닙니다.");

        assertThatThrownBy(() -> profile.changeReferUrl(url3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 형식의 url 형식이 아닙니다.");

        assertThatThrownBy(() -> profile.changeReferUrl(url4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바른 형식의 url 형식이 아닙니다.");

    }

    @Test
    @DisplayName("올바른 url 입력")
    void input_correct_url() {
        //given
        final String url1 = "https://github.com";
        final String url2 = "https://google.com";
        final String url3 = "https://youtu.be";

        //when & then
        Profile profile = new Profile(user1);

        profile.changeReferUrl(url1);
        assertThat(profile.getReferUrl()).isEqualTo("https://github.com");

        profile.changeImageUrl(url2);
        assertThat(profile.getImageUrl()).isEqualTo("https://google.com");

        profile.changeImageUrl(url3);
        assertThat(profile.getImageUrl()).isEqualTo("https://youtu.be");
    }

    @Test
    @DisplayName("포지션 정보 추가")
    void add_user_position() {
        assertDoesNotThrow(() -> testProfile1.addPositions(Set.of(userPosition1)));
    }

}