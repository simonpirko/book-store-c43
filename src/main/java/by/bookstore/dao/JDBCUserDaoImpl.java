package by.bookstore.dao;

import by.bookstore.entity.TypeOfUser;
import by.bookstore.entity.User;
import by.bookstore.utils.MySQLConnection;

import java.sql.*;

public class JDBCUserDaoImpl implements UserDao {

    private static final String SAVE = " INSERT INTO users (name, login, password, picture, typeOfUser) VALUES (?, ?, ?, ?, ?) ";
    private static final String GET_USERS = " SELECT * FROM users ";
    private static final String BY_ID = " WHERE id = ? ";
    private static final String BY_LOGIN = "WHERE login = ? ";
    private static final String UPDATE_PASSWORD = " UPDATE users SET password = ? ";
    private static final String UPDATE_NAME = " UPDATE users SET name = ? ";
    @Override
    public boolean save(User user) {
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getPicture());
            preparedStatement.setString(5, user.getTypeOfUser().name());
            return preparedStatement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public User getByLogin(String login) {
        User userByLogin = new User();
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS + BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            getUserFromResult(userByLogin, resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return userByLogin;
    }

    @Override
    public boolean updateName(User user, String newName) {
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NAME + BY_ID);
            preparedStatement.setString(1, newName);
            preparedStatement.setLong(2, user.getId());
            return preparedStatement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePassword(User user, String newPassword) {
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD + BY_ID);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setLong(2, user.getId());
            return preparedStatement.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isExistById(long id) {
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_USERS + BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isExistByLogin(String login) {
        try (Connection connection = MySQLConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_USERS + BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    private  void getUserFromResult(User userByLogin, ResultSet resultSet) throws SQLException{
        while (resultSet.next()) {
            userByLogin = new User(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("picture"),
                    TypeOfUser.valueOf(resultSet.getString("typeOfUser"))
            );
        }
    }
}

