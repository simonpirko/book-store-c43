package by.bookstore.dao;

import by.bookstore.entity.TypeOfUser;
import by.bookstore.entity.User;
import by.bookstore.utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDaoImpl implements UserDao {
    @Override
    public boolean save(User user) {
        try (Connection connection = MySQLConnection.getConnection()) {
            String sql = "INSERT INTO users (name, login, password, picture, typeOfUser) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
    public User getById(int id) {
        List<User> userById = new ArrayList<>();
        try (Connection connection = MySQLConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userById.add(new User(
                        resultSet.getString("name"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("picture"),
                        TypeOfUser.valueOf(resultSet.getString("typeOfUser"))
                ));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return userById.get(0);
    }

    @Override
    public boolean updateName(User user, String newName) {
        try (Connection connection = MySQLConnection.getConnection()) {
            String sql = "UPDATE users SET name = ? WHERE name = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, user.getName());
            preparedStatement.execute();
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePassword(User user, String oldPassword, String newPassword) {
        try (Connection connection = MySQLConnection.getConnection()) {
            String sql = "UPDATE users SET password = ? WHERE login = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.execute();
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isExistById(int id) {
        try (Connection connection = MySQLConnection.getConnection()) {
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
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
            String sql = "SELECT * FROM users WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
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
