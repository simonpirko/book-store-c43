package by.bookstore.service;

import by.bookstore.dao.BookDAO;
import by.bookstore.entity.Book;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
public class BookService {
    private BookDAO bookDAO;


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


    public boolean deleteBookById(long id){             // на фасаде сделать проверку  user на typeOfUser
        if(bookDAO.isExistById(id)){
            return bookDAO.deleteById(id);
        }
        return false;
    }

    public boolean updateBook(Book book){               // на фасаде сделать проверку  user на typeOfUser
        if(bookDAO.isExistById(book.getId())){
            return bookDAO.updateBook(book);
        }
        return false;
    }

    public boolean updateBookRating(long idBook, double rating){
        if(bookDAO.isExistById(idBook)){
            return bookDAO.updateRating(idBook, rating);
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


