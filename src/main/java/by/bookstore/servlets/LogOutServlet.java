package by.bookstore.servlets;

import by.bookstore.service.FacadeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet
public class
LogOutServlet extends HttpServlet {
    private final FacadeService facade = new FacadeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        facade.resetReservedStatusAfterLogOut();
        req.getSession().invalidate();
        resp.sendRedirect("/");
    }
}
