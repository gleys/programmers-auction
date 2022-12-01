package com.example.programmers_auction.user.domain;

import com.example.programmers_auction.common.BaseEntity;
import com.example.programmers_auction.user.presentation.IllegalUserException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@Table(name = "users")
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor(access = PROTECTED)
public class User extends BaseEntity {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)*@(?:\\w+\\.)+\\w+$");
    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[가-힣A-Za-z0-9]{2,10}");

    @Column(name = "user_id")
    @Id @GeneratedValue
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "user_name", nullable = false)
    private String displayName;

    @Column(name = "is_certified", nullable = false)
    private boolean isVerify;

    @Column(nullable = false)
    private String verifyCode;

    public User(final String email, final String displayName) {
        validateEmail(email);
        validateUserName(displayName);

        this.email = email;
        this.displayName = displayName;
        this.isVerify = false;
        this.verifyCode = generateVerifyCode();
    }

    public String generateVerifyCode() {
        return UUID.randomUUID().toString();
    }

    private void validateEmail(final String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalUserException("올바른 이메일 형식이 아닙니다.");
        }
    }

    private void validateUserName(final String name) {
        Matcher matcher = USERNAME_PATTERN.matcher(name);
        if (!matcher.matches()) {
            throw new IllegalUserException("올바른 닉네임 형식이 아닙니다.");
        }
    }
}
