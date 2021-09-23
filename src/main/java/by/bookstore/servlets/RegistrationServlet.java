package by.bookstore.servlets;

import by.bookstore.entity.TypeOfUser;
import by.bookstore.entity.User;
import by.bookstore.service.FacadeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet
public class RegistrationServlet extends HttpServlet {
    private final FacadeService facadeService = new FacadeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String URL = req.getParameter("picture");
        User user = new User(name, login, password, URL, TypeOfUser.USER);
        if(facadeService.registration(user)){
            req.setAttribute("message_reg", "Registration passed successfully");
        }else req.setAttribute("message_reg", "Its user has existed yet" );
        req.getServletContext().getRequestDispatcher("").forward(req, resp);
    }
}
