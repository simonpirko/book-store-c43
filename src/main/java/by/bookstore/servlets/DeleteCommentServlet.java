package by.bookstore.servlets;

import by.bookstore.entity.Comment;
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
import java.util.Optional;

@WebServlet(name = "DeleteCommentServlet", urlPatterns = "/deleteComm")
public class DeleteCommentServlet extends HttpServlet {
    private final FacadeService facadeService = new FacadeService();
    private final Logger logger = LoggerFactory.getLogger(DeleteCommentServlet.class.getSimpleName());
    private long commentId = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        commentId = Long.parseLong(req.getParameter("idComment"));
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Comment comment = new Comment(commentId);
        if (facadeService.deleteComment(comment, user)) {
            req.setAttribute("message_remove_com", "ok");
            logger.info("Successful deletion of a comment (id = {}).", comment.getId());
        } else {
            req.setAttribute("message_remove_com", "error");
        }
        resp.sendRedirect("/bookStore");
    }
}
