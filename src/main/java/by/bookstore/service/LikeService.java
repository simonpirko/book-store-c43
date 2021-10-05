package by.bookstore.service;

import by.bookstore.dao.LikeDAO;
import by.bookstore.entity.Like;

import java.sql.Connection;
import java.util.List;

public class LikeService {
    private LikeDAO likeDAO;

    public LikeService() {}

    public LikeService(LikeDAO likeDAO) {
        this.likeDAO = likeDAO;
    }

    public boolean saveLike (Like like, Connection connection) {
        if (!likeDAO.isExistByUserAndBook(like, connection)) {
            return likeDAO.save(like, connection);
        }
        return false;
    }

    public List<Like> getLikesByBook(long idBook, Connection connection) {
        return likeDAO.getLikesByBook(idBook, connection);
    }
}