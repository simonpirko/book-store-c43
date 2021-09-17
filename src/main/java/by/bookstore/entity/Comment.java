package by.bookstore.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comment {
    private long id;
    private LocalDateTime time;
    private User user;
    private String description;
    private Book book;

    public Comment(LocalDateTime time, User user, String description, Book book) {
        this.time = time;
        this.user = user;
        this.description = description;
        this.book = book;
    }

    public Comment(long id, LocalDateTime time, String description) {
        this.id = id;
        this.time = time;
        this.description = description;
    }

    public Comment(long id, LocalDateTime time, User user, String description, Book book) {
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
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

