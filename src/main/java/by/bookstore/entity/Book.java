package by.bookstore.entity;

import java.util.List;
import java.util.Objects;

public class Book {
    private long id;
    private String name;
    private List<Comment> comments;
    private int likes;
    private double rating;

    public Book(long id, String name, List<Comment> comments, int likes, double rating) {
        this.id = id;
        this.name = name;
        this.comments = comments;
        this.likes = likes;
        this.rating = rating;
    }

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && likes == book.likes && Double.compare(book.rating, rating) == 0 && Objects.equals(name, book.name) && Objects.equals(comments, book.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, comments, likes, rating);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comments=" + comments +
                ", likes=" + likes +
                ", rating=" + rating +
                '}';
    }
}
