package by.bookstore.dao;

import by.bookstore.entity.Book;
import by.bookstore.entity.User;

import java.util.List;
import java.util.Optional;

public interface BookDAO {

    boolean saveBook(Book book);

    boolean updateBook(Book book);

    boolean updateBookReservedStatus(long bookId, boolean flag);

    boolean updateBookOwner(long bookId, long idUser);

    boolean deleteById(long id);

    boolean isExistById(long id);

    boolean isExistByNameAuthor(String name, String author);

    Book getBookById(long id);

    Book getBookByNameAuthor(String name, String author);

    List<Book> getAllBooks();

    List<Book> getBooksByUser(long userId);

    List<Book> getReservedBookByUser(long userId, boolean flag);

    boolean updateRating(long id, double rating);
}
