package com.example.programmers_auction.profile.domain;

import com.example.programmers_auction.common.BaseEntity;
import com.example.programmers_auction.user.domain.User;
import com.example.programmers_auction.user_position.domain.UserPosition;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "profiles")
@EqualsAndHashCode(of = "id", callSuper=false)
@NoArgsConstructor(access = PROTECTED)
public class Profile extends BaseEntity {

    private final static Pattern URL_PATTERN = Pattern.compile("^((http|https)://)?(www.)?([a-zA-Z0-9]+)\\.[a-z]+([a-zA-z0-9.?#]+)?");

    @Id
    @Column(name = "profile_id")
    private long id;

    @Column(name = "refer_url", nullable = true)
    private String referUrl;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @MapsId
    @OneToOne(fetch = LAZY, orphanRemoval = true)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_users_to_profiles"))
    private User user;

    @OneToMany(mappedBy = "profile", orphanRemoval = true, cascade = PERSIST)
    private Set<UserPosition> positions = new HashSet<>();

    public Profile(final User user) {
        this.user = user;
    }

    public void addPositions(final Set<UserPosition> positions) {
        this.positions.addAll(positions);
    }

    public void changeReferUrl(final String referUrl) {
        validateUrl(referUrl);
        this.referUrl = referUrl;
    }

    public void changeImageUrl(final String imageUrl) {
        validateUrl(imageUrl);
        this.imageUrl = imageUrl;
    }

    public void validateUrl(final String url) {
        Matcher matcher = URL_PATTERN.matcher(url);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("올바른 형식의 url 형식이 아닙니다.");
        }
    }


}
