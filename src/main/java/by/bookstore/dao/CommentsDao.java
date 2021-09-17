package by.bookstore.dao;

import by.bookstore.entity.Book;
import by.bookstore.entity.Comment;
import by.bookstore.entity.User;

import java.util.List;

public interface CommentsDao {

    boolean save(Comment comment);

    boolean deleteById(int commentId);

    boolean update(Comment comment);

    User getById(int commentId);

    List<Comment> getAllByBookId(int bookId);

    List<Comment> getAllByUserId(int userId);

    List<Comment> getAllByUserIdAndBookId(int userId, int bookId);

    boolean isExistByInfo(Comment comment);

    boolean isExistById(int commentId);

}

