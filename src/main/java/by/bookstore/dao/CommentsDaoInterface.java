package by.bookstore.dao;

import by.bookstore.entity.Book;
import by.bookstore.entity.Comment;
import by.bookstore.entity.User;

import java.util.List;

public interface CommentsDaoInterface {

    void saveComment(Comment comment);

    void deleteComment(Comment comment);

    void updateComment(Comment comment);

    List<Comment> getAllCommentsByUser(User user);

    List<Comment> getAllCommentsByBook(Book book);

    List<Comment> getAllCommentsByCommentId(Comment comment);

    List<Comment> getAllCommentsByBookId(Book book);

    List<Comment> getAllCommentsByUserId(User user);

    boolean isExistByCommentId(Comment comment);

    //существует ли в базе по сообщению юзеру и книге. Вот этот не понял немного.Нужно пояснение!
}

