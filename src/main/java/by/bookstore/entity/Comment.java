package by.bookstore.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Comment {
    private long id;
    private Timestamp time;
    private User user;
    private String description;
    private Book book;

    public Comment(Timestamp time, User user, String description, Book book) {
        this.time = time;
        this.user = user;
        this.description = description;
        this.book = book;
    }

    public Comment(long id, Timestamp time, String description) {
        this.id = id;
        this.time = time;
        this.description = description;
    }

    public Comment(long id, Timestamp time, User user, String description, Book book) {
        this.id = id;
        this.time = time;
        this.user = user;
        this.description = description;
        this.book = book;
    }

    public Comment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(user, comment.user) && Objects.equals(description, comment.description) && Objects.equals(book, comment.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, description, book);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", time=" + time +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", book=" + book +
                '}';
    }
}

