package by.bookstore.dao;

import by.bookstore.entity.Book;
import by.bookstore.entity.Like;
import by.bookstore.entity.TypeOfUser;
import by.bookstore.entity.User;
import by.bookstore.utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCLikeDaoImpl implements LikeDAO {
    @Override
    public boolean save(Like like) {
        try (Connection connection = MySQLConnection.getConnection()) {
            String query = "INSERT INTO likes (user_id, book_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, like.getUser().getId());
            statement.setLong(2, like.getBook().getId());
            statement.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isExistByUserAndBook(Like like) {
        try (Connection connection = MySQLConnection.getConnection()) {
            String query = "SELECT * FROM likes WHERE user_id = ? and book_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, like.getUser().getId());
            statement.setLong(2, like.getBook().getId());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Like> getLikesByBook(int idBook) {
        List<Like> likeList = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection()) {
            String query = "SELECT likes.id AS like_id, u.id AS id_user," +
                    " u.name AS name_user, u.typeOfUser, u.picture, b.id AS id_book, b.name AS name_book," +
                    " b.author FROM likes JOIN books b on b.id = likes.book_id JOIN users u on u.id = b.user_id " +
                    " WHERE book_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, idBook);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                likeList.add(new Like(
                        resultSet.getLong("like_id"),
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
        }
        return likeList;
    }
}
