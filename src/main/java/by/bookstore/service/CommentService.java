package by.bookstore.service;

import by.bookstore.dao.CommentsDao;
import by.bookstore.dao.JDBCCommentsDaoImpl;
import by.bookstore.entity.Comment;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CommentService {
    private CommentsDao commentsDao;

    public CommentService(CommentsDao commentsDao) {
        this.commentsDao = commentsDao;
    }

    public CommentService() {
    }

    public CommentsDao getCommentsDao() {
        return commentsDao;
    }

    public boolean save(Comment comment, Connection connection) {
        return !commentsDao.isExistByInfo(comment, connection) && commentsDao.save(comment, connection);
    }

    public boolean delete(long commentId, Connection connection){
        return commentsDao.isExistById(commentId, connection) && commentsDao.deleteById(commentId, connection);
    }

    public List<Comment> getAllByUserId(long userId, Connection connection){
        return commentsDao.getAllByUserId(userId, connection);
    }

    public List<Comment>getAllByUserIdAndBookId(long userId, long bookId, Connection connection){
        return commentsDao.getAllByUserIdAndBookId(userId,bookId, connection);
    }

    public List<Comment>getAllByBookId(long bookId, Connection connection){
        return commentsDao.getAllByBookId(bookId, connection);
    }

    public Optional<Comment> getById(long commentId, Connection connection) {
        if (commentsDao.isExistById(commentId, connection)) {
            return Optional.of(commentsDao.getById(commentId, connection));
        }else return Optional.empty();
    }

    public boolean update(Comment comment, Connection connection){
        return commentsDao.isExistById(comment.getId(), connection) && commentsDao.update(comment, connection);
    }

    public List<Comment> getAllByBookIdSortByDate(long bookId, Connection connection){
        return commentsDao.getAllByBookIdSortByDate(bookId, connection);
    }

}
