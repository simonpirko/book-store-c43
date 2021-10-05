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

    //for methods: save, updateName
    private static final int NAME = 1;

    //for methods: save
    private static final int LOGIN = 2;
    private static final int PASSWORD = 3;
    private static final int PICTURE = 4;
    private static final int TYPE_USER = 5;

    //for methods: getByLogin, isExistByLogin
    private static final int LOGIN2 = 1;

    //for methods: updateName, updatePassword
    private static final int USER_ID = 2;

    //for methods: updatePassword
    private static final int PASSWORD2 = 1;

    //for methods: isExistById
    private static final int USER_ID2 = 1;

    @Override
    public boolean save(User user, Connection connection) {
        try  {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE);
            preparedStatement.setString(NAME, user.getName());
            preparedStatement.setString(LOGIN, user.getLogin());
            preparedStatement.setString(PASSWORD, user.getPassword());
            preparedStatement.setString(PICTURE, user.getPicture());
            preparedStatement.setString(TYPE_USER, user.getTypeOfUser().name());
            preparedStatement.execute();
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public User getByLogin(String login, Connection connection) {
        User userByLogin = new User();
        try  {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS + BY_LOGIN);
            preparedStatement.setString(LOGIN2, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            userByLogin = getUserFromResult(resultSet);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return userByLogin;
    }

    @Override
    public boolean updateName(User user, String newName, Connection connection) {
        try  {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NAME + BY_ID);
            preparedStatement.setString(NAME, newName);
            preparedStatement.setLong(USER_ID, user.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePassword(User user, String newPassword, Connection connection) {
        try  {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD + BY_ID);
            preparedStatement.setString(PASSWORD2, newPassword);
            preparedStatement.setLong(USER_ID, user.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isExistById(long id, Connection connection) {
        try  {
            PreparedStatement statement = connection.prepareStatement(GET_USERS + BY_ID);
            statement.setLong(USER_ID2, id);
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
    public boolean isExistByLogin(String login, Connection connection) {
        try  {
            PreparedStatement statement = connection.prepareStatement(GET_USERS + BY_LOGIN);
            statement.setString(LOGIN2, login);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    private  User getUserFromResult(ResultSet resultSet) throws SQLException{
        User userByLogin = new User();
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
        return userByLogin;
    }
}

