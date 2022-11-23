package com.example.programmerauction.domain;

import com.example.programmerauction.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "profiles")
@NoArgsConstructor(access = PROTECTED)
public class Profile extends BaseEntity {

    private final static Pattern URL_PATTERN = Pattern.compile("^((http|https)://)?(www.)?([a-zA-Z0-9]+)\\.[a-z]+([a-zA-z0-9.?#]+)?");

    @Id
    @Column(name = "profile_id")
    private long id;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "refer_url", nullable = true)
    private String referUrl;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Profile(final String position) {
        this.position = position;
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
