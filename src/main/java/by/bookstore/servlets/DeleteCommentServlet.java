package by.bookstore.servlets;

import by.bookstore.entity.Comment;
import by.bookstore.entity.User;
import by.bookstore.service.FacadeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet()
public class DeleteCommentServlet extends HttpServlet {
    private FacadeService facadeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long commentId = Long.parseLong(req.getParameter("idComment"));
        User user = (User) req.getSession().getAttribute("user");
        if (facadeService.getComment(commentId).isPresent()) {
            Comment comment = facadeService.getComment(commentId).get();
            if (facadeService.delete(comment, user)) {
                req.getSession().setAttribute("message_remove_com", "ok");
            } else {
                req.getSession().setAttribute("message_remove_com", "error");
            }
        }
        getServletContext().getRequestDispatcher("").forward(req, resp);
    }
}
