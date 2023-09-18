package com.ZTPAI2023.GoldenOaks.transaction;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "transactions")
public class Transaction {
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "transaction_date")
    private Date transactionDate;
    @Column(name = "book_received")
    private Date bookReceived;
    @Column(name = "comments")
    private String comments;

    public Transaction() {}

    public Transaction(Long bookId, Date transactionDate, Date bookReceived, String comments) {
        this.bookId = bookId;
        this.transactionDate = transactionDate;
        this.bookReceived = bookReceived;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Date getBookReceived() {
        return bookReceived;
    }

    public void setBookReceived(Date bookReceived) {
        this.bookReceived = bookReceived;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", transactionDate=" + transactionDate +
                ", bookReceived=" + bookReceived +
                ", comments='" + comments + '\'' +
                '}';
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) && Objects.equals(bookId, that.bookId) && Objects.equals(transactionDate, that.transactionDate) && Objects.equals(bookReceived, that.bookReceived) && Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookId, transactionDate, bookReceived, comments);
    }
}
