package by.bookstore.servlets.user;

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

@WebServlet(name = "UpdateNameUserServlet", urlPatterns = "/updateNameUser")
public class UpdateNameUserServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(UpdateNameUserServlet.class.getSimpleName());
    private final FacadeService facadeService = new FacadeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/account/changeName.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String newName = req.getParameter("newName");
        Connection connection = (Connection) req.getSession().getAttribute("connection");

        if (facadeService.changeName(user, newName, connection)){
            req.setAttribute("message_upd_n", "Successfully update name!");
            user.setName(newName);
            logger.info("Update name for user: {}", user.getLogin());
        }else {
            req.setAttribute("message_upd_n", "The new name cannot be the same as the old one!");
        }
        req.getServletContext().getRequestDispatcher("/account/changeName.jsp").forward(req, resp);
    }
}
