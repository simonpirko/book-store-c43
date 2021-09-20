package by.bookstore.service.comment;

import by.bookstore.dao.CommentsDao;
import by.bookstore.dao.JDBCCommentsDaoImpl;
import by.bookstore.entity.Comment;

import java.util.List;

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

    public void setCommentsDao(CommentsDao commentsDao) {
        this.commentsDao = commentsDao;
    }

    public boolean save(Comment comment) {
        return commentsDao.save(comment);
    }

    public boolean delete(long commentId){
        if (commentsDao.isExistById(commentId)){
            commentsDao.deleteById(commentId);
        }return false;
    }

    public List<Comment> getAllByUserId(long userId){
        return commentsDao.getAllByUserId(userId);
    }

    public List<Comment>getAllByUserIdAndBookId(long userId, long bookId){
        return commentsDao.getAllByUserIdAndBookId(userId,bookId);
    }

    public List<Comment>getAllByBookId(long bookId){
        return commentsDao.getAllByBookId(bookId);
    }

    public Comment getById(long commentId){
        return commentsDao.getById(commentId);
    }

    public boolean update(Comment comment){
        return commentsDao.update(comment);
    }

    public List<Comment> getAllByBookIdSortByDate(long bookId){
        return commentsDao.getAllByBookIdSortByDate(bookId);
    }

}
