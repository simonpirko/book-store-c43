package by.bookstore.service;

import by.bookstore.dao.BookDAO;
import by.bookstore.entity.Book;
import by.bookstore.entity.User;

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

    public boolean saveInBasket(Long bookId) {
        if (!basketBook.contains(bookId)) {
            basketBook.add(bookId);
            bookDAO.updateBookReservedStatus(bookId, true);
            return true;
        }
        return false;
    }

    public List<Book> getReservedBooks(){
        List<Book> books = new ArrayList<>();
        for (Long l : basketBook){
            books.add(bookDAO.getBookById(l));
        }
        return books;
    }

    public void setStatusOwnerAfterPurchase(User user){
        basketBook.forEach(x -> bookDAO.updateBookOwner(x, user.getId()));
        resetReservedStatusAfterLogOutOrPurchase();
    }

    public void resetReservedStatusAfterLogOutOrPurchase(){
        basketBook.forEach(x -> bookDAO.updateBookReservedStatus(x, false));
    }
}
