package com.ZTPAI2023.GoldenOaks.transaction;

public class TransactionNotFoundException extends RuntimeException {
    TransactionNotFoundException(Long id) {
        super("Transaction with id " + id + " not found.");
    }
}
