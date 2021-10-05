package by.bookstore.servlets.comment;

import by.bookstore.entity.Book;
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
import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;
//
@WebServlet(name = "AddCommentServlet", urlPatterns = "/addComment")
public class AddCommentServlet extends HttpServlet {
    private final FacadeService facadeService = new FacadeService();
    private final Logger logger = LoggerFactory.getLogger(AddCommentServlet.class.getSimpleName());
    private long bookId = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        bookId = Long.parseLong(req.getParameter("bookId"));
        getServletContext().getRequestDispatcher("/comment/commentAdd.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("description");
        User user = (User) req.getSession().getAttribute("user");
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        Book book = new Book(bookId);
        Comment comment = new Comment(timestamp, user, description, book);

        Connection connection = (Connection) req.getSession().getAttribute("connection");

        if (facadeService.addComment(comment, connection)) {
            req.setAttribute("message_add_com", "Comment added!");
            logger.info("{} added new comment for book ({} {}).", user.getName(), book.getName(),book.getAuthor());
        } else {
            req.setAttribute("message_add_com", "Comment adding error!");
        }
        getServletContext().getRequestDispatcher("/comment/commentAdd.jsp").forward(req, resp);
    }
}
