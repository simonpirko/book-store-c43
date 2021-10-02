package by.bookstore.service;

import by.bookstore.entity.*;

import java.util.List;
import java.util.Optional;

public class FacadeService {

    public boolean registration(User user) {
        return Dependencies.userService.saveUser(user);
    }

    public Optional<User> authorization(User user) {
        return Dependencies.userService.authorization(user);
    }

    public boolean changePassword(User user, String newPassword, String oldPassword) {
        return Dependencies.userService.updatePassword(user, oldPassword, newPassword);
    }

    public boolean changeName(User user, String name) {
        return Dependencies.userService.updateName(user, name);
    }

    public boolean addBook(Book book) {
        return Dependencies.bookService.saveBookByNameAuthor(book);
    }

    public boolean deleteBook(User user, long idBook) {
        Optional<Book> book = Dependencies.bookService.getBookById(idBook);
        if (book.isPresent()) {
            if (book.get().getUser().getId() == user.getId() || user.getTypeOfUser().equals(TypeOfUser.ADMIN)) {
                return Dependencies.bookService.deleteBookById(idBook);
            }
        }
        return false;
    }

    public List<Book> getBooksByUserId(long idUser) {
        List<Book> books = Dependencies.bookService.getBooksByUser(idUser);
        for (Book book : books) {
            book.setLikes(Dependencies.likeService.getLikesByBook(book.getId()));
            book.setComments(Dependencies.commentService.getAllByBookIdSortByDate(book.getId()));
        }
        return books;
    }

    public List<Book> getReservedBooks(BookBasket bookBasket) {
        List<Book> books = bookBasket.getReservedBooks();
        for (Book book : books) {
            book.setLikes(Dependencies.likeService.getLikesByBook(book.getId()));
            book.setComments(Dependencies.commentService.getAllByBookIdSortByDate(book.getId()));
        }
        return books;
    }

    public List<Book> getBooks() {
        List<Book> books = Dependencies.bookService.getAllBooks();
        for (Book book : books) {
            book.setLikes(Dependencies.likeService.getLikesByBook(book.getId()));
            book.setComments(Dependencies.commentService.getAllByBookIdSortByDate(book.getId()));
        }
        return books;
    }

    public boolean updateRatingBook(long idBook, int assessment) {
        return Dependencies.bookService.updateBookRating(idBook, assessment);
    }

    public boolean updateBook(User user, Book book) {
        if (user.getTypeOfUser().equals(TypeOfUser.ADMIN) || book.getUser().equals(user)) {
            return Dependencies.bookService.updateBook(book);
        } else return false;
    }

    public boolean addComment(Comment comment) {
        return Dependencies.commentService.save(comment);
    }

    public boolean deleteComment(Comment comment, User user) {
        Optional<Comment> commentOptional = Dependencies.commentService.getById(comment.getId());
        if (commentOptional.isPresent()) {
            if (commentOptional.get().getUser().getId() == user.getId() || user.getTypeOfUser().equals(TypeOfUser.ADMIN)) {
                return Dependencies.commentService.delete(comment.getId());
            } else return false;
        } else return false;
    }

    public List<Comment> getCommentsByUser(long idUser) {
        return Dependencies.commentService.getAllByUserId(idUser);
    }

    public List<Comment> getCommentByUserAndBook(long idUser, long idBook) {
        return Dependencies.commentService.getAllByUserIdAndBookId(idUser, idBook);
    }

    public List<Comment> getCommentsByBook(long idBook) {
        return Dependencies.commentService.getAllByBookId(idBook);
    }

    public Optional<Comment> getComment(long idComment) {
        return Dependencies.commentService.getById(idComment);
    }

    public boolean updateComment(Comment comment) {
        return Dependencies.commentService.update(comment);
    }

    public List<Comment> getCommentByBookBySorted(long idBook) {
        return Dependencies.commentService.getAllByBookIdSortByDate(idBook);
    }

    public boolean addLike(Like like) {
        return Dependencies.likeService.saveLike(like);
    }

    public List<Like> getLikesByBook(long idBook) {
        return Dependencies.likeService.getLikesByBook(idBook);
    }

    public boolean addBookInBasket(long idBook, BookBasket bookBasket) {
        return bookBasket.saveInBasket(idBook);
    }

    public void confirmPurchase(User user, BookBasket bookBasket) {
        bookBasket.setStatusOwnerAfterPurchase(user);
    }

    public void resetReservedStatusBookAfterLogOut(BookBasket bookBasket) {
        bookBasket.resetReservedStatusAfterLogOutOrPurchase();
    }

    public Book getBookById(long bookId) {
        Optional<Book> bookById = Dependencies.bookService.getBookById(bookId);
        if (bookById.isPresent()) {
            Book book = bookById.get();
            book.setComments(Dependencies.commentService.getAllByBookId(bookId));
            book.setLikes(Dependencies.likeService.getLikesByBook(bookId));
            return book;
        }
        return new Book();
    }
}
