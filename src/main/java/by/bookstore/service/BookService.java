package by.bookstore.service;

import by.bookstore.dao.BookDAO;
import by.bookstore.entity.Book;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@NoArgsConstructor
public class BookService {
    private BookDAO bookDAO;


    public Optional<Book> getBookById(long idBook, Connection connection){
        if(bookDAO.isExistById(idBook, connection)){
            return Optional.of(bookDAO.getBookById(idBook, connection));
        }else return Optional.empty();
    }

    public boolean saveBookById(Book book, Connection connection){
        if(bookDAO.isExistById(book.getId(), connection)){
            return bookDAO.saveBook(book, connection);
        }
        return false;
    }

    public boolean saveBookByNameAuthor(Book book, Connection connection){
        if(!bookDAO.isExistByNameAuthor(book.getName(), book.getAuthor(), connection)){
            return bookDAO.saveBook(book, connection);
        }
        return false;
    }


    public boolean deleteBookById(long id, Connection connection){
        if(bookDAO.isExistById(id, connection)){
            return bookDAO.deleteById(id, connection);
        }
        return false;
    }

    public boolean updateBook(Book book, Connection connection){
        if(bookDAO.isExistById(book.getId(), connection)){
            return bookDAO.updateBook(book, connection);
        }
        return false;
    }

    public boolean updateBookRating(long idBook, int rating, Connection connection){
        if(bookDAO.isExistById(idBook, connection)){
            double oldRating = getBookById(idBook, connection).get().getRating();
            double newRating = (oldRating + rating) / 2;
            return bookDAO.updateRating(idBook, newRating, connection);
        }
        return false;
    }

    public List<Book> getAllBooks(Connection connection){
        return bookDAO.getAllBooks(connection);
    }


    public List<Book> getBooksByUser(long idUser, Connection connection){
        return bookDAO.getBooksByUser(idUser, connection);
    }

    public List<Book> getReservedBookByUser(long idUser, Connection connection){
        return bookDAO.getReservedBookByUser(idUser, true, connection);
    }
}


