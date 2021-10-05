package by.bookstore.service;

import by.bookstore.dao.BookDAO;
import by.bookstore.entity.Book;
import by.bookstore.entity.User;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BookBasket {
    private BookDAO bookDAO;
    private final List<Long> basketBook = new ArrayList<>();

    public BookBasket(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public BookBasket() {
    }

    public boolean saveInBasket(Long bookId, Connection connection) {
        if (!basketBook.contains(bookId)) {
            basketBook.add(bookId);
            bookDAO.updateBookReservedStatus(bookId, true, connection);
            return true;
        }
        return false;
    }

    public List<Book> getReservedBooks(Connection connection){
        List<Book> books = new ArrayList<>();
        for (Long l : basketBook){
            books.add(bookDAO.getBookById(l, connection));
        }
        return books;
    }

    public void setStatusOwnerAfterPurchase(User user, Connection connection){
        basketBook.forEach(x -> bookDAO.updateBookOwner(x, user.getId(), connection));
        resetReservedStatusAfterLogOutOrPurchase(connection);
    }

    public void resetReservedStatusAfterLogOutOrPurchase(Connection connection){
        basketBook.forEach(x -> bookDAO.updateBookReservedStatus(x, false, connection));
        basketBook.clear();
    }
}
