package com.example.programmers_auction.user.dto.response;

import com.example.programmers_auction.user.domain.User;
import lombok.Data;

@Data
public class UserCreateResponse {
    private Long userId;
    private String email;
    private String displayName;

    public UserCreateResponse(final User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.displayName = user.getDisplayName();
    }
}
