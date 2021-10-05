package by.bookstore.servlets.like;

import by.bookstore.entity.Book;
import by.bookstore.entity.Like;
import by.bookstore.entity.User;
import by.bookstore.service.FacadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "AddLikeServlet", urlPatterns = "/addLike")
public class AddLikeServlet extends HttpServlet {
    private final FacadeService facade = new FacadeService();
    private final Logger logger = LoggerFactory.getLogger(AddLikeServlet.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long bookId = Long.parseLong(req.getParameter("book_id"));
        Book book = new Book(bookId);
        User user = (User) req.getSession().getAttribute("user");
        Like like = new Like(user, book);
        Connection connection = (Connection) req.getSession().getAttribute("connection");
        if(facade.addLike(like, connection)){
            logger.info("{} added new like for book ({} {}).", user.getName(), book.getName(),book.getAuthor());
        }
        resp.sendRedirect("/bookStore");
    }
}