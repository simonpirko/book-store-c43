package by.bookstore.service;

import by.bookstore.dao.BookDAO;
import by.bookstore.entity.Book;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@NoArgsConstructor
public class BookService {
    private BookDAO bookDAO;


    public Optional<Book> getBookById(long idBook){
        if(bookDAO.isExistById(idBook)){
            return Optional.of(bookDAO.getBookById(idBook));
        }else return Optional.empty();
    }


    public boolean saveBookById(Book book){
        if(bookDAO.isExistById(book.getId())){
            return bookDAO.saveBook(book);
        }
        return false;
    }

    public boolean saveBookByNameAuthor(Book book){
        if(bookDAO.isExistByNameAuthor(book.getName(), book.getAuthor())){
            return bookDAO.saveBook(book);
        }
        return false;
    }


    public boolean deleteBookById(long id){
        if(bookDAO.isExistById(id)){
            return bookDAO.deleteById(id);
        }
        return false;
    }

    public boolean updateBook(Book book){
        if(bookDAO.isExistById(book.getId())){
            return bookDAO.updateBook(book);
        }
        return false;
    }

    public boolean updateBookRating(long idBook, int rating){
        if(bookDAO.isExistById(idBook)){
            double oldRating = getBookById(idBook).get().getRating();
            double newRating = (oldRating + rating) / 2;
            return bookDAO.updateRating(idBook, newRating);
        }
        return false;
    }

    public List<Book> getAllBooks(){
        return bookDAO.getAllBooks();
    }


    public List<Book> getBooksByUser(long idUser){
        return bookDAO.getBooksByUser(idUser);
    }

    public List<Book> getReservedBookByUser(long idUser){
        return bookDAO.getReservedBookByUser(idUser, true);
    }


}


