package by.bookstore.dao;

import by.bookstore.entity.Book;
import by.bookstore.entity.Comment;
import by.bookstore.entity.TypeOfUser;
import by.bookstore.entity.User;
import by.bookstore.utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JDBCCommentsDaoImpl implements CommentsDao {

    @Override
    public boolean save(Comment comment) {
        try (Connection connection = MySQLConnection.getConnection()) {
            String query = "INSERT INTO comments VALUES (default,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, comment.getTime());
            statement.setLong(2, comment.getUser().getId());
            statement.setString(3, comment.getDescription());
            statement.setLong(4, comment.getBook().getId());
            return statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(long commentId) {
        try (Connection connection = MySQLConnection.getConnection()) {
            String query = "DELETE FROM comments WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, commentId);
            return statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Comment comment) {
        try (Connection connection = MySQLConnection.getConnection()) {
            String query = "UPDATE comments SET description = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
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
            String query = "SELECT comments.id AS comments_id, comments.time, comments.description, " +
                    " b.id AS book_id, b.name, b.author, u.id AS user_id, u.name, u.picture, u.typeOfUser" +
                    " FROM comments JOIN books b on b.id = comments.book_id" +
                    " JOIN users u on u.id = comments.user_id WHERE comments.id = ? ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, commentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            comment = new Comment(resultSet.getLong("comments_id"),
                    new Date(resultSet.getTime("time").getTime()).toLocalDate().atStartOfDay(),
                    new User(
                            resultSet.getLong("user_id"),
                            resultSet.getString("name"),
                            resultSet.getString("picture"),
                            TypeOfUser.valueOf(resultSet.getString("typeOfUser"))
                    ),
                    resultSet.getString("description"),
                    new Book(
                            resultSet.getLong("book_id"),
                            resultSet.getString("name"),
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
        try (Connection connection = MySQLConnection.getConnection()) {
            String query = "SELECT comments.id AS comments_id, comments.time, comments.description, " +
                    " b.id AS book_id, b.name, b.author, u.id AS user_id, u.name, u.picture, u.typeOfUser" +
                    " FROM comments JOIN books b on b.id = comments.book_id" +
                    " JOIN users u on u.id = comments.user_id WHERE b.id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                commentList.add(new Comment(
                        resultSet.getLong("comments_id"),
                        new Date(resultSet.getTime("time").getTime()).toLocalDate().atStartOfDay(),
                        new User(
                                resultSet.getLong("user_id"),
                                resultSet.getString("name"),
                                resultSet.getString("picture"),
                                TypeOfUser.valueOf(resultSet.getString("typeOfUser"))
                        ),
                        resultSet.getString("description"),
                        new Book(
                                resultSet.getLong("book_id"),
                                resultSet.getString("name"),
                                resultSet.getString("author")
                        )));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return commentList;
    }

    @Override
    public List<Comment> getAllByUserId(long userId) {
        List<Comment> commentList = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection()) {
            String query = "SELECT comments.id AS comments_id, comments.time, comments.description, " +
                    " b.id AS book_id, b.name, b.author, u.id AS user_id, u.name, u.picture, u.typeOfUser" +
                    " FROM comments JOIN users u on u.id = comments.user_id" +
                    " JOIN books b on b.id = comments.book_id WHERE u.id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                commentList.add(new Comment(
                        resultSet.getLong("comments_id"),
                        new Date(resultSet.getTime("time").getTime()).toLocalDate().atStartOfDay(),
                        new User(
                                resultSet.getLong("user_id"),
                                resultSet.getString("name"),
                                resultSet.getString("picture"),
                                TypeOfUser.valueOf(resultSet.getString("typeOfUser"))
                        ),
                        resultSet.getString("description"),
                        new Book(
                                resultSet.getLong("book_id"),
                                resultSet.getString("name"),
                                resultSet.getString("author")
                        )));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return commentList;
    }


    @Override
    public List<Comment> getAllByUserIdAndBookId(long userId, long bookId) {
            List<Comment> commentList = new ArrayList<>();
            try (Connection connection = MySQLConnection.getConnection()) {
                String query = "SELECT comments.id AS comments_id, comments.time, comments.description, " +
                        " b.id AS book_id, b.name, b.author, u.id AS user_id, u.name, u.picture, u.typeOfUser" +
                        " FROM comments JOIN users u on u.id = comments.user_id" +
                        " JOIN books b on b.id = comments.book_id WHERE u.id = ? AND b.id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setLong(1, userId);
                statement.setLong(2, bookId);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    commentList.add(new Comment(
                            resultSet.getLong("comment_id"),
                            new Date(resultSet.getTime("time").getTime()).toLocalDate().atStartOfDay(),
                            new User(
                                    resultSet.getLong("id_user"),
                                    resultSet.getString("name"),
                                    resultSet.getString("picture"),
                                    TypeOfUser.valueOf(resultSet.getString("typeOfUser"))
                            ),
                            resultSet.getString("description"),
                            new Book(
                                    resultSet.getLong("book_id"),
                                    resultSet.getString("name"),
                                    resultSet.getString("author")
                            )));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return commentList;
        }

    @Override
    public boolean isExistByInfo(Comment comment) {
        try (Connection connection = MySQLConnection.getConnection()) {
            String query = "SELECT * FROM comments WHERE (user_id = ? && book_id = ? && description = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, comment.getUser().getId());
            preparedStatement.setLong(2, comment.getBook().getId());
            preparedStatement.setString(3, comment.getDescription());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isExistById(long commentId) {
        try (Connection connection = MySQLConnection.getConnection()) {
            String query = "SELECT * FROM comments WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, commentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Comment> getAllByBookIdSortByDate(long bookId) {
        List<Comment> comments = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection()) {
            String query = "SELECT comments.id AS comments_id, comments.time, comments.description, " +
                    " b.id AS book_id, b.name, b.author, u.id AS user_id, u.name, u.picture, u.typeOfUser" +
                    " FROM comments JOIN books b on b.id = comments.book_id" +
                    " JOIN users u on u.id = comments.user_id WHERE b.id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                comments.add(new Comment(
                        resultSet.getLong("comments_id"),
                        new Date(resultSet.getTime("time").getTime()).toLocalDate().atStartOfDay(), //Задействован один из способов приведения типа данных Date -> LocalDate, требуется тестировние с текущими настройками БД.
                        new User(resultSet.getInt("user_id"),
                                resultSet.getString("name"),
                                resultSet.getString("picture"),
                                TypeOfUser.valueOf(resultSet.getString("typeOfUser"))
                        ),
                        resultSet.getString("description"),
                        new Book(resultSet.getInt("book_id"),
                                resultSet.getString("name"),
                                resultSet.getString("author"))
                ));
            }
            comments.sort(Comparator.comparing(Comment::getTime));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comments;
    }
}
