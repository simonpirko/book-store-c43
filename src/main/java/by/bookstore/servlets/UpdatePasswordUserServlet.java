package by.bookstore.servlets;

import by.bookstore.entity.User;
import by.bookstore.service.FacadeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdatePasswordUserServlet", urlPatterns = "/updatePasswordUser")
public class UpdatePasswordUserServlet extends HttpServlet {

    private final FacadeService facadeService = new FacadeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/changePassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String newPassword = req.getParameter("newPassword");
        String oldPassword = req.getParameter("oldPassword");
        if (facadeService.changePassword(user, newPassword, oldPassword)){
            req.setAttribute("message_upd_p", "Successfully update password");
        }else {
            req.setAttribute("message_upd_p", "Unsuccessfully update password");
        }
        req.getServletContext().getRequestDispatcher("/changePassword.jsp").forward(req, resp);
    }
}
