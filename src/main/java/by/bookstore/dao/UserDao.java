package by.bookstore.dao;

import by.bookstore.entity.User;

import java.util.List;

public interface UserDao {

    boolean save(User user);

    User getByLogin(String login);

    boolean updateName(User user, String newName);

    boolean updatePassword(User user,String oldPassword, String newPassword);

    boolean isExistById(int id);

    boolean isExistByLogin(String login);



}
