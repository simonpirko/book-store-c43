package by.bookstore.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Comment {
    private long id;
    private LocalDateTime time;
    private User user;
    private String description;
    private int likes;

    public Comment(long id, LocalDateTime time, User user, String description, int likes) {
        this.id = id;
        this.time = time;
        this.user = user;
        this.description = description;
        this.likes = likes;
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && likes == comment.likes && Objects.equals(time, comment.time) && Objects.equals(user, comment.user) && Objects.equals(description, comment.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, user, description, likes);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", time=" + time +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", likes=" + likes +
                '}';
    }
}
