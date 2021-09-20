package by.bookstore.service;

import by.bookstore.dao.UserDao;
import by.bookstore.entity.User;

import java.util.Optional;

public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserService() {}

    public boolean saveUser(User user){
        if(userDao.isExistByLogin(user.getLogin())){
            return userDao.save(user);
        }
        return false;
    }

    public Optional<User> authorization(User user){
        if(userDao.isExistById(user.getId())){
            return Optional.of(userDao.getByLogin(user.getLogin()));
        }
        return Optional.empty();
    }

    public boolean updateName(User user, String newName){
        if (!user.getName().equals(newName)){
            return userDao.updateName(user, newName);
        }
        return false;
    }

    public boolean updatePassword(User user, String oldPassword, String newPassword){
        if (user.getPassword().equals(oldPassword) && !user.getPassword().equals(newPassword)){
            return userDao.updatePassword(user, newPassword);
        }
        return false;
    }
}
