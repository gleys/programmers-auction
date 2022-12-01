package com.example.programmers_auction.skill.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "skills")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = PROTECTED)
public class Skill {

    @Id @GeneratedValue
    @Column(name = "skill_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    public Skill(final String name) {
        this.name = name;
    }
}
