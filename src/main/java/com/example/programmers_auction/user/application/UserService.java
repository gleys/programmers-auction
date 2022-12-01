package com.example.programmers_auction.user.application;

import com.example.programmers_auction.user.domain.User;
import com.example.programmers_auction.user.domain.UserRepository;
import com.example.programmers_auction.user.dto.request.UserCreateRequest;
import com.example.programmers_auction.user.dto.response.UserCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserCreateResponse create(final UserCreateRequest request) {
        User user = new User(request.getEmail(), request.getDisplayName());
        userRepository.save(user);
        return new UserCreateResponse(user);
    }

    public void delete(final Long userId) {
        userRepository.deleteById(userId);
    }

    private void validateUserDelete(final User user) {

    }
}
