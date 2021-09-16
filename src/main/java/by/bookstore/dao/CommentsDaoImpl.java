package by.bookstore.dao;

import by.bookstore.entity.Book;
import by.bookstore.entity.Comment;
import by.bookstore.entity.User;
import by.bookstore.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CommentsDaoImpl implements CommentsDaoInterface{
//    final String URL = "jdbc:mysql://localhost:3306/book_store?serverTimezone=UTC";
//    final String USER = "root";
//    final String PASSWORD = "root";


    @Override
    public void saveComment(Comment comment) {
        try(Connection connection = MySQLConnection.getConnection()) {
            String sqlSaveComment = "INSERT INTO book_store.comments VALUES (default,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sqlSaveComment);
            statement.setObject(1,comment.getTime());
            statement.setInt(2, (int) comment.getUser().getId());
            statement.setString(3,comment.getDescription());
            statement.setInt(4, (int) comment.getBook().getId());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteComment(Comment comment) {
        try(Connection connection = MySQLConnection.getConnection()) {
            String sqlDeleteComment = "DELETE FROM book_store.comments WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sqlDeleteComment);
            statement.setInt(1, (int) comment.getId());
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateComment(Comment comment) {

    }

    @Override
    public List<Comment> getAllCommentsByUser(User user) {
        return null;
    }

    @Override
    public List<Comment> getAllCommentsByBook(Book book) {
        return null;
    }

    @Override
    public List<Comment> getAllCommentsByCommentId(Comment comment) {
        return null;
    }

    @Override
    public List<Comment> getAllCommentsByBookId(Book book) {
        return null;
    }

    @Override
    public List<Comment> getAllCommentsByUserId(User user) {
        return null;
    }

    @Override
    public boolean isExistByCommentId(Comment comment) {
        return false;
    }
}
