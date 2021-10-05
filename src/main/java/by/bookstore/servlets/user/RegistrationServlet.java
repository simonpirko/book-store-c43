package by.bookstore.servlets.user;

import by.bookstore.entity.TypeOfUser;
import by.bookstore.entity.User;
import by.bookstore.service.FacadeService;
import by.bookstore.utils.MySQLConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/reg")
public class RegistrationServlet extends HttpServlet {
    private final FacadeService facadeService = new FacadeService();
    private final Logger logger = LoggerFactory.getLogger(RegistrationServlet.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/account/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String URL = req.getParameter("picture");

        User user = new User(name, login, password, URL, TypeOfUser.USER);
        try {
            if (facadeService.registration(user, MySQLConnection.getConnection())) {
                req.setAttribute("message_reg", "Registration passed successfully");
                logger.info("Register new user: {}", user.getName());
            } else {
                req.setAttribute("message_reg", "Its user has existed yet");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        req.getServletContext().getRequestDispatcher("/account/registration.jsp").forward(req, resp);
    }
}
