package com.example.programmers_auction.user.presentation;

public class IllegalUserException extends RuntimeException{

    public IllegalUserException(String message) {
        super(message);
    }
}
