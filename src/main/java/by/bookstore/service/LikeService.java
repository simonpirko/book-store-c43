package by.bookstore.service;

import by.bookstore.dao.LikeDAO;
import by.bookstore.entity.Like;

import java.util.List;

public class LikeService {
    private LikeDAO likeDAO;

    public LikeService() {}

    public LikeService(LikeDAO likeDAO) {
        this.likeDAO = likeDAO;
    }

    public boolean saveLike (Like like) {
        if (likeDAO.isExistByUserAndBook(like)) {
            return likeDAO.save(like);
        }
        return false;
    }

    public List<Like> getLikesByBook(long idBook) {
        return likeDAO.getLikesByBook(idBook);
    }
}