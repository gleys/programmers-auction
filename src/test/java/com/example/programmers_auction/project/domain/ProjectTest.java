package com.example.programmers_auction.project.domain;

import com.example.programmers_auction.common.fixture.ProjectFixture;
import com.example.programmers_auction.project.exception.InvalidBiddingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.programmers_auction.common.fixture.CategoryFixture.childCategory1;
import static com.example.programmers_auction.common.fixture.UserFixture.user1;
import static com.example.programmers_auction.project.domain.ProjectType.SELL;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ProjectTest {

    @Test
    @DisplayName("프로젝트 등록")
    void create_project() {
        String title = "쇼핑몰 만들어 드립니다.";
        String description = "경력은 없지만 잘 만들 자신 있습니다.";

        assertDoesNotThrow(
                () -> new Project(
                    childCategory1,
                    user1,
                    SELL,
                    title,
                    description,
                    100000L,
                    10000L
                )
        );
    }

    @Test
    @DisplayName("입찰 가능 상태가 아닐 때 입찰시 예외")
    void not_access_bidding() {
        Project buyProject = ProjectFixture.buyProject1();
        Project sellProject = ProjectFixture.sellProject1();

        List<ProjectStatus> notAccessStatus = Arrays.stream(ProjectStatus.values())
                .filter(status -> !status.equals(ProjectStatus.PENDING))
                .collect(Collectors.toList());

        for (ProjectStatus status : notAccessStatus) {
            buyProject.changeStatus(status);
            sellProject.changeStatus(status);

            assertThatThrownBy(
                () -> buyProject.participateBidding(1L)
            )
            .isInstanceOf(InvalidBiddingException.class)
            .hasMessage("입찰 가능 상태가 아닙니다.");

            assertThatThrownBy(
                () -> sellProject.participateBidding(1L)
            )
            .isInstanceOf(InvalidBiddingException.class)
            .hasMessage("입찰 가능 상태가 아닙니다.");
        }
    }

    @Test
    @DisplayName("구매 입찰 시 현재가 이상이면 예외")
    void not_valid_current_price() {
        Project buyProject = ProjectFixture.buyProject1();
        assertThatThrownBy(
                () -> buyProject.participateBidding(200000L)
        )
                .isInstanceOf(InvalidBiddingException.class)
                .hasMessage("구매글 입찰은 항상 현재가 보다 낮아야 하고 최소 단위로만 입찰 가능합니다.");
    }

    @Test
    @DisplayName("입찰 시 최소 단위로 떨어지도록 입찰 하지 않으면 예외")
    void not_valid_unit_price() {
        Project buyProject = ProjectFixture.buyProject1();
        Project sellProject = ProjectFixture.sellProject1();

        assertThatThrownBy(
                () -> buyProject.participateBidding(93000L)
        )
        .isInstanceOf(InvalidBiddingException.class)
        .hasMessage("구매글 입찰은 항상 현재가 보다 낮아야 하고 최소 단위로만 입찰 가능합니다.");

        assertThatThrownBy(
                () -> sellProject.participateBidding(10100L)
        )
                .isInstanceOf(InvalidBiddingException.class)
                .hasMessage("판매글 입찰은 항상 현재가 보다 높아야 하고 최소 단위로만 입찰 가능합니다.");
    }

    @Test
    @DisplayName("정상 입찰")
    void valid_bidding() {
        Project buyProject = ProjectFixture.buyProject1();
        Project sellProject = ProjectFixture.sellProject1();

        assertDoesNotThrow(() -> buyProject.participateBidding(90000L));
        assertDoesNotThrow(() -> sellProject.participateBidding(110000L));
    }




}