package by.bookstore.dao;

import by.bookstore.entity.Like;

import java.sql.Connection;
import java.util.List;

public interface LikeDAO {

    boolean save(Like like, Connection connection);

    boolean isExistByUserAndBook(Like like, Connection connection);

    List<Like> getLikesByBook(long idBook, Connection connection);

    }

