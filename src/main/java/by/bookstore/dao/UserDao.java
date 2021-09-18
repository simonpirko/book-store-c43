package by.bookstore.dao;

import by.bookstore.entity.User;

public interface UserDao {

    boolean save(User user);

    User getByLogin(String login);

    boolean updateName(User user, String newName);

    boolean updatePassword(User user, String newPassword);

    boolean isExistById(long id);

    boolean isExistByLogin(String login);



}
