package by.bookstore.dao;

import by.bookstore.entity.Comment;

import java.sql.Connection;
import java.util.List;

public interface CommentsDao {

    boolean save(Comment comment, Connection connection);

    boolean deleteById(long commentId, Connection connection);

    boolean update(Comment comment, Connection connection);

    Comment getById(long commentId, Connection connection);

    List<Comment> getAllByBookId(long bookId, Connection connection);

    List<Comment> getAllByUserId(long userId, Connection connection);

    List<Comment> getAllByUserIdAndBookId(long userId, long bookId, Connection connection);

    boolean isExistByInfo(Comment comment, Connection connection);

    boolean isExistById(long commentId, Connection connection);

    List<Comment> getAllByBookIdSortByDate(long bookId, Connection connection);

}

