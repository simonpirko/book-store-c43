package by.bookstore.dao;

import by.bookstore.entity.Book;
import by.bookstore.entity.Comment;
import by.bookstore.entity.TypeOfUser;
import by.bookstore.entity.User;
import by.bookstore.utils.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
            String sqlUpdateComment = "UPDATE comments SET description = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sqlUpdateComment);
            statement.setString(1, comment.getDescription());
            statement.setLong(2, comment.getId());
            return statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Comment getById(long commentId) {
        Comment comment = new Comment();
        try (Connection connection = MySQLConnection.getConnection()) {
            String sqlGetCommentById = "SELECT comments.id AS comments_id, comments.time, comments.description, " +
                    "b.id AS book_id, b.name, b.author, u.id AS user_id, u.name, u.picture, u.typeOfUser" +
                    "FROM comments JOIN books b on b.id = comments.book_id" +
                    "JOIN users u on u.id = comments.user_id WHERE comments.id = ? ";
            PreparedStatement statement = connection.prepareStatement(sqlGetCommentById);
            statement.setLong(1, commentId);
            ResultSet resultSet = statement.executeQuery();
            new Comment(
                    resultSet.getLong("comment_id"),
                    new User(
                            resultSet.getLong("id_user"),
                            resultSet.getString("name_user"),
                            resultSet.getString("picture"),
                            TypeOfUser.valueOf(resultSet.getString("typeOfUser"))
                    ),
                    new Book(
                            resultSet.getLong("id_book"),
                            resultSet.getString("name_book"),
                            resultSet.getString("author")
                    ));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comment;
    }

    @Override
    public List<Comment> getAllByBookId(long bookId) {
        List<Comment> commentList = new ArrayList<>();
        try(Connection connection = MySQLConnection.getConnection()) {
            String sqlGetCommentByBook = "SELECT comments.id AS comments_id, comments.time, comments.description, " +
                    " b.id AS book_id, b.name, b.author, u.id AS user_id, u.name, u.picture, u.typeOfUser" +
                    " FROM comments JOIN books b on b.id = comments.book_id" +
                    " JOIN users u on u.id = comments.user_id WHERE b.id = ?";
            PreparedStatement statement = connection.prepareStatement(sqlGetCommentByBook);
            statement.setLong(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                commentList.add(new Comment(
                        resultSet.getLong("comment_id"),
                        new User(
                                resultSet.getLong("id_user"),
                                resultSet.getString("name_user"),
                                resultSet.getString("picture"),
                                TypeOfUser.valueOf(resultSet.getString("typeOfUser"))
                        ),
                        new Book(
                                resultSet.getLong("id_book"),
                                resultSet.getString("name_book"),
                                resultSet.getString("author")
                        )));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }return commentList;
    }

    @Override
    public List<Comment> getAllByUserId(long userId) {
        List<Comment> commentList = new ArrayList<>();
        try(Connection connection = MySQLConnection.getConnection()) {
            String sqlGetCommentByUser = "SELECT comments.id AS comments_id, comments.time, comments.description, " +
                    " b.id AS book_id, b.name, b.author, u.id AS user_id, u.name, u.picture, u.typeOfUser" +
                    " FROM comments JOIN users u on u.id = comments.user_id" +
                    " JOIN books b on b.id = comments.book_id WHERE u.id = ?";
            PreparedStatement statement = connection.prepareStatement(sqlGetCommentByUser);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                commentList.add(new Comment(
                        resultSet.getLong("comment_id"),
                        new User(
                                resultSet.getLong("id_user"),
                                resultSet.getString("name_user"),
                                resultSet.getString("picture"),
                                TypeOfUser.valueOf(resultSet.getString("typeOfUser"))
                        ),
                        new Book(
                                resultSet.getLong("id_book"),
                                resultSet.getString("name_book"),
                                resultSet.getString("author")
                        )));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }return commentList;
    }

    @Override
    public List<Comment> getAllByUserIdAndBookId(long userId, long bookId) {
        List<Comment> commentList = new ArrayList<>();
      try (Connection connection = MySQLConnection.getConnection()) {
          String sqlGetCommentsByUserAndBook = "SELECT comments.id AS comments_id, comments.time, comments.description, " +
                  " b.id AS book_id, b.name, b.author, u.id AS user_id, u.name, u.picture, u.typeOfUser" +
                  " FROM comments JOIN users u on u.id = comments.user_id" +
                  " JOIN books b on b.id = comments.book_id WHERE u.id = ? AND b.id = ?";
          PreparedStatement statement = connection.prepareStatement(sqlGetCommentsByUserAndBook);
         statement.setLong(1, userId);
         statement.setLong(2, bookId);
          ResultSet resultSet = statement.executeQuery();
          while (resultSet.next()) {
              commentList.add(new Comment(
                      resultSet.getLong("comment_id"),
                      new User(
                              resultSet.getLong("id_user"),
                              resultSet.getString("name_user"),
                              resultSet.getString("picture"),
                              TypeOfUser.valueOf(resultSet.getString("typeOfUser"))
                      ),
                      new Book(
                              resultSet.getLong("id_book"),
                              resultSet.getString("name_book"),
                              resultSet.getString("author")
                      )));
          }

      } catch (SQLException throwables) {
          throwables.printStackTrace();
      }   return commentList;
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
