package by.bookstore.service;

import by.bookstore.dao.BookDAO;
import by.bookstore.entity.Book;
import by.bookstore.entity.User;

import java.util.ArrayList;
import java.util.List;

public class BookBasket {
    private BookDAO bookDAO;
    private List<Book> basketBook = new ArrayList<>();

    public BookBasket(BookDAO bookDAO, List<Book> basketBook) {
        this.bookDAO = bookDAO;
        this.basketBook = basketBook;
    }

    public BookBasket() {
    }

    public boolean saveInBasket(Book book, User user) {
        if (!basketBook.contains(book)) {
            bookDAO.updateBookReservedStatus(book.getId(), true);
            basketBook.addAll(bookDAO.getReservedBookByUser(user.getId(), true));
            return true;
        }
        return false;
    }

    public List<Book> getBasketBooks() {
        return basketBook;
    }

    public void setStatusOwnerAfterPurchase(User user){
        basketBook.forEach(x -> bookDAO.updateBookOwner(x.getId(), user.getId()));
    }
}
