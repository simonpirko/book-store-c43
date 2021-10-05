package by.bookstore.dao;

import by.bookstore.entity.User;

import java.sql.Connection;

public interface UserDao {

    boolean save(User user, Connection connection);

    User getByLogin(String login, Connection connection);

    boolean updateName(User user, String newName, Connection connection);

    boolean updatePassword(User user, String newPassword, Connection connection);

    boolean isExistById(long id, Connection connection);

    boolean isExistByLogin(String login, Connection connection);



}
