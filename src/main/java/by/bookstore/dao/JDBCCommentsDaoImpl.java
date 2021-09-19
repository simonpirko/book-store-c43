package by.bookstore.dao;

import by.bookstore.entity.Book;
import by.bookstore.entity.Comment;
import by.bookstore.entity.User;
import by.bookstore.utils.MySQLConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
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
        try(Connection connection = MySQLConnection.getConnection()){
            String isExistById = "SELECT * FROM comments WHERE (user_id = ? && book_id = ? && description = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(isExistById);
            preparedStatement.setLong(1,comment.getUser().getId());
            preparedStatement.setLong(2,comment.getBook().getId());
            preparedStatement.setString(3,comment.getDescription());
            ResultSet resultSet = preparedStatement.executeQuery(isExistById);
            return resultSet.next();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }return false;
    }

    @Override
    public boolean isExistById(long commentId) {
        try(Connection connection = MySQLConnection.getConnection()){
            String isExistById = "SELECT * FROM comments WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(isExistById);
            preparedStatement.setLong(1,commentId);
            ResultSet resultSet = preparedStatement.executeQuery(isExistById);
            return resultSet.next();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }return false;
    }

    @Override
    public List<Comment> getAllByBookIdSortByDate(long bookId) {
        List<Comment> comments = new ArrayList<>();
        try(Connection connection = MySQLConnection.getConnection()){
            String isExistById = "SELECT * FROM comments WHERE book_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(isExistById);
            preparedStatement.setLong(1,bookId);
            ResultSet resultSet = preparedStatement.executeQuery(isExistById);
            while (resultSet.next()){
                comments.add(new Comment(
                        resultSet.getLong("id"),
                        new Date(resultSet.getTime("time").getTime()).toLocalDate().atStartOfDay(), //Задействован один из способов приведения типа данных Date -> LocalDate, требуется тестировние с текущими настройками БД.
                        new User(resultSet.getInt("user_id")),
                        resultSet.getString("description"),
                        new Book(resultSet.getInt("book_id"))
                ));
            }
            comments.sort(Comparator.comparing(Comment::getTime));
            return comments;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }return comments;
    }
}
