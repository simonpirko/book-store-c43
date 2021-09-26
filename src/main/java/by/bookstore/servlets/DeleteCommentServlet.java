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
import java.util.Optional;

@WebServlet(name = "DeleteCommentServlet", urlPatterns = "/deleteComm")
public class DeleteCommentServlet extends HttpServlet {
    private final FacadeService facadeService = new FacadeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long commentId = Long.parseLong(req.getParameter("idComment"));
        User user = (User) req.getSession().getAttribute("user");
        Optional<Comment> optionalComment = facadeService.getComment(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            if (facadeService.deleteComment(comment, user)) {
                req.setAttribute("message_remove_com", "ok");
            } else {
                req.setAttribute("message_remove_com", "error");
            }
        }
        getServletContext().getRequestDispatcher("").forward(req, resp);
    }
}
