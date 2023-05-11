package com.ZTPAI2023.GoldenOaks;

public class UserAccountNotFoundException extends RuntimeException {
    UserAccountNotFoundException(Long id) {
        super("User with id " + id + " not found.");
    }
}
