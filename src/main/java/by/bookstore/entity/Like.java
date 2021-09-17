package by.bookstore.entity;

import java.util.Objects;

public class Like {
    private long id;
    private User user;
    private Book book;

    public Like(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public Like(long id, User user, Book book) {
        this.id = id;
        this.user = user;
        this.book = book;
    }

    public Like() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return Objects.equals(user, like.user) && Objects.equals(book, like.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, book);
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
