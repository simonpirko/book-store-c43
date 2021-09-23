package by.bookstore.servlets;

import by.bookstore.entity.User;
import by.bookstore.service.FacadeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet
public class AuthorizationServlet extends HttpServlet {

    private final FacadeService facadeService = new FacadeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<User> optionalUser = facadeService.authorization(new User(login,password));
        if(optionalUser.isPresent()){
            req.getSession().setAttribute("user", optionalUser.get());
            req.setAttribute("message_SignIn", "Authorization passed successfully");
        }else req.setAttribute("message_SignIn", "Its user hasn't registered yet");
        req.getServletContext().getRequestDispatcher("").forward(req, resp);
    }
}
