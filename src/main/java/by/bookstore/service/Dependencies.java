package by.bookstore.service;

import by.bookstore.dao.JDBCBookDAOImpl;
import by.bookstore.dao.JDBCCommentsDaoImpl;
import by.bookstore.dao.JDBCLikeDaoImpl;
import by.bookstore.dao.JDBCUserDaoImpl;

import java.util.ArrayList;

public class Dependencies {

    public final static BookBasket bookBasket = new BookBasket(new JDBCBookDAOImpl());
    public final static BookService bookService = new BookService(new JDBCBookDAOImpl());
    public final static CommentService commentService= new CommentService(new JDBCCommentsDaoImpl());
    public final static LikeService likeService = new LikeService(new JDBCLikeDaoImpl());
    public final static UserService userService = new UserService(new JDBCUserDaoImpl());
}
