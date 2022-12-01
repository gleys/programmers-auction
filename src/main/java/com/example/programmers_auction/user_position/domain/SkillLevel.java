package com.example.programmers_auction.user_position.domain;

import com.example.programmers_auction.user_position.exception.InvalidLevel;

import java.util.List;

public enum SkillLevel {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

    private final int level;

    SkillLevel(final int value) {
        this.level = value;
    }

    public static SkillLevel from(final int value) {
        if(!isValid(value)) {
            throw new InvalidLevel("유효하지 않은 레벨입니다.");
        }

        return SkillLevel.values()[value - 1];
    }

    private static boolean isValid(int value) {
        return List.of(SkillLevel.values()).contains(value);
    }
}
