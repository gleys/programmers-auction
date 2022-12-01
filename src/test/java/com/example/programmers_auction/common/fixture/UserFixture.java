package com.example.programmers_auction.common.fixture;

import com.example.programmers_auction.user.domain.User;

public class UserFixture {

    public static final String userEmail1 = "userA@example.com";
    public static final String displayName1 = "userA";

    public static User user1 = new User(userEmail1, displayName1);
}
