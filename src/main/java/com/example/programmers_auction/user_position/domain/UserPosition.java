package com.example.programmers_auction.user_position.domain;

import com.example.programmers_auction.common.BaseEntity;
import com.example.programmers_auction.position.domain.Position;
import com.example.programmers_auction.profile.domain.Profile;
import com.example.programmers_auction.skill.domain.Skill;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "user_positions")
@NoArgsConstructor(access = PROTECTED)
@EqualsAndHashCode(of = "id", callSuper = false)
public class UserPosition extends BaseEntity {

    @Column(name = "user_positions_id")
    @Id @GeneratedValue
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(
        name = "skill_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_user_positions_to_skills")
    )
    private Skill skill;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "skill_level", nullable = false)
    private SkillLevel skillLevel;

    @Column(name = "description", nullable = true)
    private String description;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "position_id", foreignKey = @ForeignKey(name = "fk_user_position_to_position"))
    private Position position;

    public void editDescription(final String description) {
        this.description = description;
    }

    public void changeLevel(final SkillLevel skillLevel) {
        this.skillLevel = skillLevel;
    }

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "profile_id", foreignKey = @ForeignKey(name = "fk_user_position_to_profile"))
    private Profile profile;

    public UserPosition(final Skill skill, final SkillLevel skillLevel, final String description, final Position position, final Profile profile) {
        this.skill = skill;
        this.skillLevel = skillLevel;
        this.description = description;
        this.position = position;
        this.profile = profile;
    }
}
