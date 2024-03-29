package com.ZTPAI2023.GoldenOaks.history;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "history")
public class History {
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    @Column(name = "id_user")
    private int idUser;
    @Column(name = "id_transaction")
    private int idTransaction;
    @Column(name = "book_received")
    private Status bookReceived;

    public History() {}

    public History(int idUser, int idTransaction, Status bookReceived) {
        this.idUser = idUser;
        this.idTransaction = idTransaction;
        this.bookReceived = bookReceived;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public Status getBookReceived() {
        return bookReceived;
    }

    public void setBookReceived(Status bookReceived) {
        this.bookReceived = bookReceived;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof History))
            return false;
        History history = (History) o;
        return Objects.equals(this.id, history.id) && Objects.equals(this.idUser, history.idUser)
                && Objects.equals(this.idTransaction, history.idTransaction) && this.bookReceived == history.bookReceived;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.idUser, this.idTransaction, this.bookReceived);
    }

    @Override
    public String toString() {
        return "History{" + "id=" + this.id + ", user id=" + this.idUser + ", transaction id=" + this.idTransaction
                + ", book received=" + this.bookReceived + "}";
    }
}
