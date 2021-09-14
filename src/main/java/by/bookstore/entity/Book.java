package by.bookstore.entity;

import java.util.List;
import java.util.Objects;

public class Book {
    private long id;
    private String name;
    private String author;
    private List<Like> likes;
    private List<Comment> comments;
    private double rating;
    private double price;
    private boolean reserved;
    private User user;


    public Book(long id, String name, String author, List<Like> likes, List<Comment> comments, double rating, double price, boolean reserved, User
                user) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.likes = likes;
        this.comments = comments;
        this.rating = rating;
        this.price = price;
        this.reserved = reserved;
        this.user = user;
    }

    public Book(String name, String author, List<Like> likes, List<Comment> comments, double rating, double price, boolean reserved, User user) {
        this.name = name;
        this.author = author;
        this.likes = likes;
        this.comments = comments;
        this.rating = rating;
        this.price = price;
        this.reserved = reserved;
        this.user = user;
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


    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", likes=" + likes +
                ", comments=" + comments +
                ", rating=" + rating +
                ", price=" + price +
                ", reserved=" + reserved +
                '}';
    }
}
