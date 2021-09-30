package by.bookstore.servlets;

import by.bookstore.entity.User;
import by.bookstore.service.FacadeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserBookServlet", urlPatterns = "/userBooks")
public class UserBookServlet extends HttpServlet   {
    private final FacadeService facadeService = new FacadeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("userBooks", facadeService.getBooksById(user.getId()));
        req.getServletContext().getRequestDispatcher("/userBooks.jsp").forward(req, resp);
    }
}
