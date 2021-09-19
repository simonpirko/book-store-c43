package by.bookstore.dao;

import by.bookstore.entity.Comment;
import by.bookstore.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletionException;

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
        try (Connection connection = MySQLConnection.getConnection()) {
            String sqlUpdateComment = "UPDATE comments SET description = ?";
            PreparedStatement statement = connection.prepareStatement(sqlUpdateComment);
            statement.setString(1, comment.getDescription());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Comment getById(long commentId) {
        try (Connection connection = MySQLConnection.getConnection()) {
            String sqlGetComment = "SELECT * FROM comments WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sqlGetComment);
            statement.setLong(1, commentId);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Comment> getAllByBookId(long bookId) {
        try(Connection connection = MySQLConnection.getConnection()) {
            String sqlGetCommentsByBook = "SELECT * FROM comments WHERE book_id = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlGetCommentsByBook);
            statement.setLong(1, bookId);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }return null;
    }

    @Override
    public List<Comment> getAllByUserId(long userId) {
        try(Connection connection = MySQLConnection.getConnection()) {
            String sqlGetCommentsByUser = "SELECT * FROM comments WHERE user_id = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlGetCommentsByUser);
            statement.setLong(1, userId);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }return null;
    }

    @Override
    public List<Comment> getAllByUserIdAndBookId(long userId, long bookId) {
      try (Connection connection = MySQLConnection.getConnection()) {
          String sqlGetCommentsByUserAndBook = "SELECT * FROM comments WHERE user_id = ? AND book_id = ?";
          PreparedStatement statement = connection.prepareStatement(sqlGetCommentsByUserAndBook);
         statement.setLong(1, userId);
         statement.setLong(2, bookId);

      } catch (SQLException throwables) {
          throwables.printStackTrace();
      } return null;
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
