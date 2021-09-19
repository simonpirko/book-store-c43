package by.bookstore.dao;

import by.bookstore.entity.TypeOfUser;
import by.bookstore.entity.User;
import by.bookstore.utils.MySQLConnection;

import java.sql.*;

public class JDBCUserDaoImpl implements UserDao {
    @Override
    public boolean save(User user) {
        try (Connection connection = MySQLConnection.getConnection()) {
            String query = "INSERT INTO users (name, login, password, picture, typeOfUser) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getPicture());
            preparedStatement.setString(5, user.getTypeOfUser().name());
            preparedStatement.execute();
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public User getByLogin(String login) {
        User userByLogin = new User();
        try (Connection connection = MySQLConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE login = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
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
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return userByLogin;
    }

    @Override
    public boolean updateName(User user, String newName) {
        try (Connection connection = MySQLConnection.getConnection()) {
            String query = "UPDATE users SET name = ? WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newName);
            preparedStatement.setLong(2, user.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePassword(User user, String newPassword) {
        try (Connection connection = MySQLConnection.getConnection()) {
            String query = "UPDATE users SET password = ? WHERE id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setLong(2, user.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isExistById(long id) {
        try (Connection connection = MySQLConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
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
            String query = "SELECT * FROM users WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(query);
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
}
