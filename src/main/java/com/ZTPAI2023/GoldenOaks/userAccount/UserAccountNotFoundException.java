package com.ZTPAI2023.GoldenOaks.userAccount;

public class UserAccountNotFoundException extends RuntimeException {
    UserAccountNotFoundException(Long id) {
        super("User with id " + id + " not found.");
    }
}
