package com.example.programmers_auction.common.fixture;

import com.example.programmers_auction.skill.domain.Skill;

public class SkillFixture {
    public static final String SKILL_NAME1 = "파이썬";

    public static Skill skill1() {
        return new Skill(SKILL_NAME1);
    }
}
