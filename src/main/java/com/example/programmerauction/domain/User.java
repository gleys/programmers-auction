package com.example.programmerauction.domain;

import com.example.programmerauction.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javax.persistence.CascadeType.PERSIST;
import static lombok.AccessLevel.*;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = PROTECTED)
public class User extends BaseEntity {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[가-힣A-Za-z0-9]{2,10}");

    @Column(name = "user_id")
    @Id @GeneratedValue
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "is_certified", nullable = false)
    private boolean isCertified;

    @OneToOne(orphanRemoval = true, cascade = PERSIST)
    @JoinTable(
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private Profile profile;

    public User(final String email, final String name) {
        validateEmail(email);
        validateUserName(name);

        this.email = email;
        this.name = name;
        this.isCertified = false;
    }

    public void setProfile(final Profile profile) {
        this.profile = profile;
    }

    private void validateEmail(final String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다.");
        }
    }

    private void validateUserName(final String name) {
        Matcher matcher = USERNAME_PATTERN.matcher(name);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("올바른 닉네임 형식이 아닙니다.");
        }
    }
}
