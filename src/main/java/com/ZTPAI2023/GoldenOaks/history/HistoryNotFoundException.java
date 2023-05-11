package com.ZTPAI2023.GoldenOaks.history;

public class HistoryNotFoundException extends RuntimeException {
    HistoryNotFoundException(Long id) {
        super("History with id " + id + " not found.");
    }
}
