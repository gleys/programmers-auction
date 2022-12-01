package com.example.programmers_auction.position.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class PositionTest {

    @Test
    @DisplayName("포지션 생성")
    void create_position() {
        String positionName = "backend";
        assertDoesNotThrow(() -> new Position(positionName));
    }

}