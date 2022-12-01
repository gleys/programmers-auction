package com.example.programmers_auction.user_position.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.example.programmers_auction.common.fixture.PositionFixture.backEnd;
import static com.example.programmers_auction.common.fixture.ProfileFixture.testProfile1;
import static com.example.programmers_auction.common.fixture.SkillFixture.skill1;

class UserPositionTest {

    @Test
    @DisplayName("유저 포지션 생성")
    void create_user_position() {
        Assertions.assertDoesNotThrow(
                () -> new UserPosition(skill1(), SkillLevel.TWO, "test", backEnd(), testProfile1)
        );
    }

}