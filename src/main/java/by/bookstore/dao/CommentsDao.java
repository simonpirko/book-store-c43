package by.bookstore.dao;

import by.bookstore.entity.Comment;

import java.util.List;

public interface CommentsDao {

    boolean save(Comment comment);

    boolean deleteById(long commentId);

    boolean update(Comment comment);

    Comment getById(long commentId);

    List<Comment> getAllByBookId(long bookId);

    List<Comment> getAllByUserId(long userId);

    List<Comment> getAllByUserIdAndBookId(long userId, long bookId);

    boolean isExistByInfo(Comment comment);

    boolean isExistById(long commentId);

}

