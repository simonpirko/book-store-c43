package by.bookstore.dao;

import by.bookstore.entity.Book;
import by.bookstore.entity.Comment;
import by.bookstore.entity.TypeOfUser;
import by.bookstore.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JDBCCommentsDaoImpl implements CommentsDao {

    private static final String SAVE = " INSERT INTO comments(time, user_id, description, book_id) VALUES (?,?,?,?) ";
    private static final String BY_ID = " WHERE  comments.id = ? ";
    private static final String DELETE = " DELETE FROM comments ";
    private static final String UPDATE = " UPDATE comments SET description = ? ";
    private static final String GET_COMMENTS = " SELECT comments.id AS comments_id, comments.time, comments.description, b.id AS book_id, b.name, b.author, u.id AS user_id, u.name AS user_name, u.picture, u.typeOfUser " +
            "FROM comments LEFT JOIN books b on b.id = comments.book_id LEFT JOIN users u on u.id = comments.user_id ";
    private static final String BY_BOOK_ID = " WHERE b.id = ? ";
    private static final String BY_USER_ID = " WHERE u.id = ? ";
    private static final String BY_INFO = " WHERE (user_id = ? AND book_id = ? AND description = ?) ";
    private static final String IS_EXIST = " SELECT * FROM comments ";

    //for methods: save
    private static final int TIME = 1;
    private static final int USER_ID = 2;
    private static final int DESCRIPTION = 3;
    private static final int BOOK_ID = 4;

    //for methods: deleteById, getById, isExistById
    private static final int COMMENT_ID = 1;

    //for methods: getAllByBookId, getAllByBookIdSortByDate
    private static final int BOOK_ID2 = 1;

    //for methods: update
    private static final int DESCRIPTION2 = 1;
    private static final int COMMENT_ID2 = 2;

    //for methods: getAllByUserId, isExistByInfo, getAllByUserIdAndBookId
    private static final int USER_ID2 = 1;
    private static final int BOOK_ID3 = 2;

    //for methods: isExistByInfo
    private static final int DESCRIPTION3 = 3;

    @Override
    public boolean save(Comment comment, Connection connection) {
        try  {
            PreparedStatement statement = connection.prepareStatement(SAVE);
            statement.setTimestamp(TIME, comment.getTime());
            statement.setLong(USER_ID, comment.getUser().getId());
            statement.setString(DESCRIPTION, comment.getDescription());
            statement.setLong(BOOK_ID, comment.getBook().getId());
            statement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(long commentId, Connection connection) {
        try  {
            PreparedStatement statement = connection.prepareStatement(DELETE + BY_ID);
            statement.setLong(COMMENT_ID, commentId);
            statement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Comment comment, Connection connection) {
        try  {
            PreparedStatement statement = connection.prepareStatement(UPDATE + BY_ID);
            statement.setString(DESCRIPTION2, comment.getDescription());
            statement.setLong(COMMENT_ID2, comment.getId());
            statement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Comment getById(long commentId, Connection connection) {
        Comment comment = new Comment();
        try  {
            PreparedStatement statement = connection.prepareStatement(GET_COMMENTS + BY_ID);
            statement.setLong(COMMENT_ID, commentId);
            ResultSet resultSet = statement.executeQuery();
            comment = getCommentFromResult(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comment;
    }

    @Override
    public List<Comment> getAllByBookId(long bookId, Connection connection) {
        List<Comment> commentList = new ArrayList<>();
        try  {
            PreparedStatement statement = connection.prepareStatement(GET_COMMENTS + BY_BOOK_ID);
            statement.setLong(BOOK_ID2, bookId);
            ResultSet resultSet = statement.executeQuery();
            commentList = getCommentsFromResult(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return commentList;
    }

    @Override
    public List<Comment> getAllByUserId(long userId, Connection connection) {
        List<Comment> commentList = new ArrayList<>();
        try  {
            PreparedStatement statement = connection.prepareStatement(GET_COMMENTS + BY_USER_ID);
            statement.setLong(USER_ID2, userId);
            ResultSet resultSet = statement.executeQuery();
            commentList = getCommentsFromResult(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return commentList;
    }

    @Override
    public List<Comment> getAllByUserIdAndBookId(long userId, long bookId, Connection connection) {
            List<Comment> commentList = new ArrayList<>();
            try  {
                PreparedStatement statement = connection.prepareStatement(GET_COMMENTS + BY_USER_ID + BY_BOOK_ID);
                statement.setLong(USER_ID2, userId);
                statement.setLong(BOOK_ID3, bookId);
                ResultSet resultSet = statement.executeQuery();
                commentList = getCommentsFromResult(resultSet);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return commentList;
        }

    @Override
    public boolean isExistByInfo(Comment comment, Connection connection) {
        try  {
            PreparedStatement preparedStatement = connection.prepareStatement(IS_EXIST + BY_INFO);
            preparedStatement.setLong(USER_ID2, comment.getUser().getId());
            preparedStatement.setLong(BOOK_ID3, comment.getBook().getId());
            preparedStatement.setString(DESCRIPTION3, comment.getDescription());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isExistById(long commentId, Connection connection) {
        try  {
            PreparedStatement preparedStatement = connection.prepareStatement(IS_EXIST + BY_ID);
            preparedStatement.setLong(COMMENT_ID, commentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Comment> getAllByBookIdSortByDate(long bookId, Connection connection) {
        List<Comment> comments = new ArrayList<>();
        try  {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_COMMENTS + BY_BOOK_ID);
            preparedStatement.setLong(BOOK_ID2, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            comments = getCommentsFromResult(resultSet);
            comments.sort(Comparator.comparing(Comment::getTime));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return comments;
    }

    private Comment getCommentFromResult(ResultSet resultSet) throws SQLException{
        Comment comment = new Comment();
        if (resultSet.next()) {
            comment = new Comment(
                    resultSet.getLong("comments_id"),
                    resultSet.getTimestamp("time"),
                    new User(
                            resultSet.getLong("user_id"),
                            resultSet.getString("user_name"),
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
        return comment;
    }

    private List<Comment> getCommentsFromResult(ResultSet resultSet) throws SQLException {
        List<Comment> commentList = new ArrayList<>();
        while (resultSet.next()) {
            commentList.add(new Comment(
                    resultSet.getLong("comments_id"),
                    resultSet.getTimestamp("time"),
                    new User(
                            resultSet.getLong("user_id"),
                            resultSet.getString("user_name"),
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
        return commentList;
    }
}
