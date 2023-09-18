package com.ZTPAI2023.GoldenOaks.book;

public class BookNotFoundException extends RuntimeException {
    BookNotFoundException(Long id) {
        super("Book with id " + id + " not found.");
    }
}
