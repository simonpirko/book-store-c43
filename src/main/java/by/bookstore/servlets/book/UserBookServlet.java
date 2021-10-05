package by.bookstore.servlets.book;

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

@WebServlet(name = "UserBookServlet", urlPatterns = "/userBooks")
public class UserBookServlet extends HttpServlet   {
    private final Logger logger = LoggerFactory.getLogger(UserBookServlet.class.getSimpleName());
    private final FacadeService facadeService = new FacadeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Connection connection = (Connection) req.getSession().getAttribute("connection");

        req.setAttribute("userBooks", facadeService.getBooksByUserId(user.getId(), connection));
        logger.info("Request for all books by user: {}", user.getName());
        req.getServletContext().getRequestDispatcher("/book/userBooks.jsp").forward(req, resp);
    }
}
