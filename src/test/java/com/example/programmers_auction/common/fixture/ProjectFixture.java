package com.example.programmers_auction.common.fixture;

import com.example.programmers_auction.project.domain.Project;

import static com.example.programmers_auction.common.fixture.CategoryFixture.parentCategory1;
import static com.example.programmers_auction.common.fixture.UserFixture.user1;
import static com.example.programmers_auction.project.domain.ProjectType.BUY;
import static com.example.programmers_auction.project.domain.ProjectType.SELL;

public class ProjectFixture {
    public static final String TITLE = "쇼핑몰 만들어 드립니다.";
    public static final String DESCRIPTION = "처음이지만 잘 할수 있습니다.";
    public static final Long OPEN_PRICE = 100000L;
    public static final Long UNIT_PRICE = 10000L;

    public static Project sellProject1() {
        return new Project(parentCategory1, user1, SELL, TITLE, DESCRIPTION, OPEN_PRICE, UNIT_PRICE);
    }

    public static Project buyProject1() {
        return new Project(parentCategory1, user1, BUY, TITLE, DESCRIPTION, OPEN_PRICE, UNIT_PRICE);
    }

}
