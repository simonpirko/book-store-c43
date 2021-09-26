package by.bookstore.servlets;

import by.bookstore.entity.User;
import by.bookstore.service.FacadeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateNameUserServlet", urlPatterns = "/updateNameUser")
public class UpdateNameUserServlet extends HttpServlet {

    private final FacadeService facadeService = new FacadeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/changeName.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String newName = req.getParameter("newName");

        if (facadeService.changeName(user, newName)){
            req.setAttribute("message_upd_n", "Successfully update name");
        }else {
            req.setAttribute("message_upd_n", "Unsuccessfully update name");
        }
        req.getServletContext().getRequestDispatcher("/changeName.jsp").forward(req, resp);

    }
}
