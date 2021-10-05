package by.bookstore.dao;

import by.bookstore.entity.Book;

import java.sql.Connection;
import java.util.List;

public interface BookDAO {

    boolean saveBook(Book book, Connection connection);

    boolean updateBook(Book book, Connection connection);

    boolean updateBookReservedStatus(long bookId, boolean flag, Connection connection);

    boolean updateBookOwner(long bookId, long idUser, Connection connection);

    boolean deleteById(long id, Connection connection);

    boolean isExistById(long id, Connection connection);

    boolean isExistByNameAuthor(String name, String author, Connection connection);

    Book getBookById(long id, Connection connection);

    Book getBookByNameAuthor(String name, String author, Connection connection);

    List<Book> getAllBooks(Connection connection);

    List<Book> getBooksByUser(long userId, Connection connection);

    List<Book> getReservedBookByUser(long userId, boolean flag, Connection connection);

    boolean updateRating(long id, double rating, Connection connection);
}
