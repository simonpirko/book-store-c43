package by.bookstore.dao;

import by.bookstore.entity.Book;
import by.bookstore.entity.User;

import java.util.List;
import java.util.Optional;

public interface BookDAO {

    void saveBook(Book book);
    void updateBook(Book book);
    void updateBookStatus(long id, boolean flag);
    void updateBookOwner(long id, long idUser);
    void deleteById(long id);
    boolean isExistById(long id);
    boolean isExistByNameAuthor(String name, String author);
    Optional<Book> getBookById(long id);
    Optional<Book> getBookByNameAuthor(String name, String author);
    List<Book> getAllBooks();
    List<Book> getBooksByUser(User user);
    List<Book> getReservedBookByUser(long userId, boolean flag);

}
