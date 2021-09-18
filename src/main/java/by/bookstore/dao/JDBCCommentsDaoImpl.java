package by.bookstore.dao;

import by.bookstore.entity.Comment;
import by.bookstore.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JDBCCommentsDaoImpl implements CommentsDao {
    @Override
    public boolean save(Comment comment) {
        try(Connection connection = MySQLConnection.getConnection()) {
            String sqlSaveComment = "INSERT INTO comments VALUES (default,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sqlSaveComment);
            statement.setObject(1,comment.getTime());
            statement.setLong(2,comment.getUser().getId());
            statement.setString(3,comment.getDescription());
            statement.setLong(4,comment.getBook().getId());
            return statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }return false;
    }

    @Override
    public boolean deleteById(long commentId) {
        try(Connection connection = MySQLConnection.getConnection()) {
            String sqlDeleteComment = "DELETE FROM comments WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sqlDeleteComment);
            statement.setLong(1, commentId);
            return statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }return false;
    }

    @Override
    public boolean update(Comment comment) {
        return false;
    }

    @Override
    public Comment getById(long commentId) {
        return null;
    }

    @Override
    public List<Comment> getAllByBookId(long bookId) {
        return null;
    }

    @Override
    public List<Comment> getAllByUserId(long userId) {
        return null;
    }

    @Override
    public List<Comment> getAllByUserIdAndBookId(long userId, long bookId) {
        return null;
    }

    @Override
    public boolean isExistByInfo(Comment comment) {
        return false;
    }

    @Override
    public boolean isExistById(long commentId) {
        return false;
    }
}
