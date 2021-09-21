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

    private static String SAVE = " INSERT INTO comments(time, user_id, description, book_id) VALUES (?,?,?,?) ";
    private static String BY_ID = " WHERE  comments.id = ? ";
    private static String DELETE = " DELETE FROM comments ";
    private static String UPDATE = " UPDATE comments SET description = ? ";
    private static String GET_COMMENTS = " SELECT comments.id AS comments_id, comments.time, comments.description, b.id AS book_id, b.name, b.author, u.id AS user_id, u.name, u.picture, u.typeOfUser " +
            "FROM comments LEFT JOIN books b on b.id = comments.book_id LEFT JOIN users u on u.id = comments.user_id ";
    private static String BY_BOOK_ID = " WHERE b.id = ? ";
    private static String BY_USER_ID = " WHERE u.id = ? ";
    private static String BY_INFO = " WHERE (user_id = ? AND book_id = ? AND description = ?) ";
    private static String IS_EXIST = " SELECT * FROM comments ";


    @Override
    public boolean save(Comment comment) {
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SAVE);
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
            PreparedStatement statement = connection.prepareStatement(DELETE + BY_ID);
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
            PreparedStatement statement = connection.prepareStatement(UPDATE + BY_ID);
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
            PreparedStatement statement = connection.prepareStatement(GET_COMMENTS + BY_ID);
            statement.setLong(1, commentId);
            ResultSet resultSet = statement.executeQuery();
            getCommentFromResult(comment, resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comment;
    }

    @Override
    public List<Comment> getAllByBookId(long bookId) {
        List<Comment> commentList = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_COMMENTS + BY_BOOK_ID);
            statement.setLong(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            getCommentsFromResult(commentList, resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return commentList;
    }



    @Override
    public List<Comment> getAllByUserId(long userId) {
        List<Comment> commentList = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_COMMENTS + BY_USER_ID);
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            getCommentsFromResult(commentList, resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return commentList;
    }


    @Override
    public List<Comment> getAllByUserIdAndBookId(long userId, long bookId) {
            List<Comment> commentList = new ArrayList<>();
            try (Connection connection = MySQLConnection.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(GET_COMMENTS + BY_USER_ID + BY_BOOK_ID);
                statement.setLong(1, userId);
                statement.setLong(2, bookId);
                ResultSet resultSet = statement.executeQuery();
                getCommentsFromResult(commentList, resultSet);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return commentList;
        }

    @Override
    public boolean isExistByInfo(Comment comment) {
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(IS_EXIST + BY_INFO);
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
            PreparedStatement preparedStatement = connection.prepareStatement(IS_EXIST + BY_ID);
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
            PreparedStatement preparedStatement = connection.prepareStatement(GET_COMMENTS + BY_BOOK_ID);
            preparedStatement.setLong(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            getCommentsFromResult(comments, resultSet);
            comments.sort(Comparator.comparing(Comment::getTime));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comments;
    }

    private void getCommentFromResult(Comment comment, ResultSet resultSet) throws SQLException{
        if (resultSet.next()) {
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
        }
    }

    private void getCommentsFromResult(List<Comment> commentList, ResultSet resultSet) throws SQLException {
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
    }
}
