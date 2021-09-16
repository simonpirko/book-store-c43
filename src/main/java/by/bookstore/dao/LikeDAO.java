package by.bookstore.dao;

import by.bookstore.entity.Like;

import java.util.List;

public interface LikeDAO {

    boolean save(Like like);

    boolean isExistByUserAndBook(Like like);

    List<Like> getLikesByBook(int idBook);

    }

