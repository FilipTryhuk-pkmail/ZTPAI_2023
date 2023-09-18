package com.ZTPAI2023.GoldenOaks.book;

import jakarta.persistence.*;

import java.util.Objects;
//(strategy=GenerationType.IDENTITY)
@Entity
@Table(name = "books")
public class Book {
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "secondary_authors")
    private String secondaryAuthors;
    @Column(name = "publishing_date")
    private String publishingDate;
    @Column(name = "current_owner")
    private Long currentOwner;
    @Column(name = "cover_art")
    private String coverArt;

    public Book() {}

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(String title, String author, String secondaryAuthors, String publishingDate, String coverArt) {
        this.title = title;
        this.author = author;
        this.secondaryAuthors = secondaryAuthors;
        this.publishingDate = publishingDate;
        this.coverArt = coverArt;
    }

    public Book(String title, String author, String publishingDate, String coverArt) {
        this.title = title;
        this.author = author;
        this.publishingDate = publishingDate;
        this.coverArt = coverArt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSecondaryAuthors() {
        return secondaryAuthors;
    }

    public void setSecondaryAuthors(String secondaryAuthors) {
        this.secondaryAuthors = secondaryAuthors;
    }

    public String getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(String publishingDate) {
        this.publishingDate = publishingDate;
    }

    public Long getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(Long currentOwner) {
        this.currentOwner = currentOwner;
    }

    public String getCoverArt() {
        return coverArt;
    }

    public void setCoverArt(String coverArt) {
        this.coverArt = coverArt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(secondaryAuthors, book.secondaryAuthors) && Objects.equals(publishingDate, book.publishingDate) && Objects.equals(currentOwner, book.currentOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, secondaryAuthors, publishingDate, currentOwner);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", secondaryAuthors='" + secondaryAuthors + '\'' +
                ", publishingDate='" + publishingDate + '\'' +
                ", currentOwner='" + currentOwner + '\'' +
                '}';
    }
}
