package com.example.programmers_auction.common.fixture;

import com.example.programmers_auction.user_position.domain.SkillLevel;
import com.example.programmers_auction.user_position.domain.UserPosition;

import static com.example.programmers_auction.common.fixture.PositionFixture.backEnd;
import static com.example.programmers_auction.common.fixture.ProfileFixture.testProfile1;
import static com.example.programmers_auction.common.fixture.SkillFixture.skill1;

public class UserPositionFixture {
    public static UserPosition userPosition1 = new UserPosition(skill1(), SkillLevel.TWO, "test", backEnd(), testProfile1);
}
