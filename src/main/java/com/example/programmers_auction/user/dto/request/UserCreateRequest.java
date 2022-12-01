package com.example.programmers_auction.user.dto.request;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String email;
    private String displayName;
}
