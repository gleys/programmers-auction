package com.example.programmers_auction.common.fixture;

import com.example.programmers_auction.position.domain.Position;

public class PositionFixture {
    public static final String positionName1 = "backend";

    public static Position backEnd() {
        return new Position(positionName1);
    }

}
