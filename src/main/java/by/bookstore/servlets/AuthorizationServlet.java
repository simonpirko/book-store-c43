package by.bookstore.servlets;

import by.bookstore.dao.JDBCBookDAOImpl;
import by.bookstore.entity.User;
import by.bookstore.service.BookBasket;
import by.bookstore.service.FacadeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "AuthorizationServlet", urlPatterns = "/auth")
public class AuthorizationServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(AuthorizationServlet.class.getSimpleName());
    private final FacadeService facadeService = new FacadeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/authorization.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<User> optionalUser = facadeService.authorization(new User(login, password));
        if (optionalUser.isPresent()) {
            req.getSession().setAttribute("user", optionalUser.get());
            req.getSession().setAttribute("basket", new BookBasket(new JDBCBookDAOImpl()));
            logger.info("Successful authorisation for user: {}", optionalUser.get().getName());
            req.setAttribute("message_SignIn", "Authorization passed successfully");
        } else {
            req.setAttribute("message_SignIn", "Its user hasn't registered yet");
        }
        req.getServletContext().getRequestDispatcher("/authorization.jsp").forward(req, resp);
    }
}
