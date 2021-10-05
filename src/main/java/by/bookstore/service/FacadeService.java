package by.bookstore.service;

import by.bookstore.entity.*;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class FacadeService {

    public boolean registration(User user, Connection connection) {
        return Dependencies.userService.saveUser(user, connection);
    }

    public Optional<User> authorization(User user, Connection connection) {
        return Dependencies.userService.authorization(user, connection);
    }

    public boolean changePassword(User user, String newPassword, String oldPassword, Connection connection) {
        return Dependencies.userService.updatePassword(user, oldPassword, newPassword, connection);
    }

    public boolean changeName(User user, String name, Connection connection) {
        return Dependencies.userService.updateName(user, name, connection);
    }

    public boolean addBook(Book book, Connection connection) {
        return Dependencies.bookService.saveBookByNameAuthor(book, connection);
    }

    public boolean deleteBook(User user, long idBook, Connection connection) {
        Optional<Book> book = Dependencies.bookService.getBookById(idBook, connection);
        if (book.isPresent()) {
            if (book.get().getUser().getId() == user.getId() || user.getTypeOfUser().equals(TypeOfUser.ADMIN)) {
                return Dependencies.bookService.deleteBookById(idBook, connection);
            }
        }
        return false;
    }

    public List<Book> getBooksByUserId(long idUser, Connection connection) {
        List<Book> books = Dependencies.bookService.getBooksByUser(idUser, connection);
        for (Book book : books) {
            book.setLikes(Dependencies.likeService.getLikesByBook(book.getId(), connection));
            book.setComments(Dependencies.commentService.getAllByBookIdSortByDate(book.getId(), connection));
        }
        return books;
    }

    public List<Book> getReservedBooks(BookBasket bookBasket, Connection connection) {
        List<Book> books = bookBasket.getReservedBooks(connection);
        for (Book book : books) {
            book.setLikes(Dependencies.likeService.getLikesByBook(book.getId(), connection));
            book.setComments(Dependencies.commentService.getAllByBookIdSortByDate(book.getId(), connection));
        }
        return books;
    }

    public List<Book> getBooks(Connection connection) {
        List<Book> books = Dependencies.bookService.getAllBooks(connection);
        for (Book book : books) {
            book.setLikes(Dependencies.likeService.getLikesByBook(book.getId(), connection));
            book.setComments(Dependencies.commentService.getAllByBookIdSortByDate(book.getId(), connection));
        }
        return books;
    }

    public boolean updateRatingBook(long idBook, int assessment, Connection connection) {
        return Dependencies.bookService.updateBookRating(idBook, assessment, connection);
    }

    public boolean updateBook(User user, Book book, Connection connection) {
        Optional<Book> bookOp = Dependencies.bookService.getBookById(book.getId(), connection);
        if (bookOp.isPresent()) {
            if (user.getTypeOfUser().equals(TypeOfUser.ADMIN) || book.getUser().getId() == bookOp.get().getUser().getId()) {
                return Dependencies.bookService.updateBook(book, connection);
            }
        }
        return false;
    }

    public boolean addComment(Comment comment, Connection connection) {
        return Dependencies.commentService.save(comment, connection);
    }

    public boolean deleteComment(Comment comment, User user, Connection connection) {
        Optional<Comment> commentOptional = Dependencies.commentService.getById(comment.getId(), connection);
        if (commentOptional.isPresent()) {
            if (commentOptional.get().getUser().getId() == user.getId() || user.getTypeOfUser().equals(TypeOfUser.ADMIN)) {
                return Dependencies.commentService.delete(comment.getId(), connection);
            } else return false;
        } else return false;
    }

    public List<Comment> getCommentsByUser(long idUser, Connection connection) {
        return Dependencies.commentService.getAllByUserId(idUser, connection);
    }

    public List<Comment> getCommentByUserAndBook(long idUser, long idBook, Connection connection) {
        return Dependencies.commentService.getAllByUserIdAndBookId(idUser, idBook, connection);
    }

    public List<Comment> getCommentsByBook(long idBook, Connection connection) {
        return Dependencies.commentService.getAllByBookId(idBook, connection);
    }

    public Optional<Comment> getComment(long idComment, Connection connection) {
        return Dependencies.commentService.getById(idComment, connection);
    }

    public boolean updateComment(Comment comment, Connection connection) {
        return Dependencies.commentService.update(comment, connection);
    }

    public List<Comment> getCommentByBookBySorted(long idBook, Connection connection) {
        return Dependencies.commentService.getAllByBookIdSortByDate(idBook, connection);
    }

    public boolean addLike(Like like, Connection connection) {
        return Dependencies.likeService.saveLike(like, connection);
    }

    public List<Like> getLikesByBook(long idBook, Connection connection) {
        return Dependencies.likeService.getLikesByBook(idBook, connection);
    }

    public boolean addBookInBasket(long idBook, BookBasket bookBasket, Connection connection) {
        return bookBasket.saveInBasket(idBook, connection);
    }

    public void confirmPurchase(User user, BookBasket bookBasket, Connection connection) {
        bookBasket.setStatusOwnerAfterPurchase(user, connection);
    }

    public void resetReservedStatusBookAfterLogOut(BookBasket bookBasket, Connection connection) {
        bookBasket.resetReservedStatusAfterLogOutOrPurchase(connection);
    }

    public Book getBookById(long bookId, Connection connection) {
        Optional<Book> bookById = Dependencies.bookService.getBookById(bookId, connection);
        if (bookById.isPresent()) {
            Book book = bookById.get();
            book.setComments(Dependencies.commentService.getAllByBookId(bookId, connection));
            book.setLikes(Dependencies.likeService.getLikesByBook(bookId, connection));
            return book;
        }
        return new Book();
    }
}
