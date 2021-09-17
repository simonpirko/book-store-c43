package by.bookstore.dao;

import by.bookstore.entity.Comment;
import by.bookstore.entity.User;
import by.bookstore.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CommentsDaoImpl implements CommentsDao {
    @Override
    public boolean save(Comment comment) {
        try(Connection connection = MySQLConnection.getConnection()) {
            String sqlSaveComment = "INSERT INTO comments VALUES (default,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sqlSaveComment);
            statement.setString(1,comment.getTime().toString());
            statement.setLong(2,comment.getUser().getId());
            statement.setString(3,comment.getDescription());
            statement.setLong(4,comment.getBook().getId());
            return statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }return false;
    }

    @Override
    public boolean deleteById(int commentId) {
        try(Connection connection = MySQLConnection.getConnection()) {
            String sqlDeleteComment = "DELETE FROM comments WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sqlDeleteComment);
            statement.setInt(1, commentId);
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
    public User getById(int commentId) {
        return null;
    }


    @Override
    public List<Comment> getAllByBookId(int bookId) {
        return null;
    }

    @Override
    public List<Comment> getAllByUserId(int userId) {
        return null;
    }

    @Override
    public List<Comment> getAllByUserAndBook(int userId, int bookId) {
        return null;
    }

    @Override
    public boolean isExistByInfo(int userId, int bookId) {
        return false;
    }

    @Override
    public boolean isExistById(int commentId) {
        return false;
    }
}
