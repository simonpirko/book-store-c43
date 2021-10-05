package by.bookstore.service;

import by.bookstore.dao.UserDao;
import by.bookstore.entity.User;

import java.sql.Connection;
import java.util.Optional;

public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserService() {}

    public boolean saveUser(User user, Connection connection){
        if(!userDao.isExistByLogin(user.getLogin(), connection)){
            return userDao.save(user, connection);
        }
        return false;
    }

    public Optional<User> authorization(User user, Connection connection){
        if(userDao.isExistByLogin(user.getLogin(), connection)){
            return Optional.of(userDao.getByLogin(user.getLogin(), connection));
        }
        return Optional.empty();
    }

    public boolean updateName(User user, String newName, Connection connection){
        if (!user.getName().equals(newName)){
            return userDao.updateName(user, newName, connection);
        }
        return false;
    }

    public boolean updatePassword(User user, String oldPassword, String newPassword, Connection connection){
        if (user.getPassword().equals(oldPassword) && !user.getPassword().equals(newPassword)){
            return userDao.updatePassword(user, newPassword, connection);
        }
        return false;
    }
}
