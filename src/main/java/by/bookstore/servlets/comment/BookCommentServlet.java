package by.bookstore.servlets.comment;

import by.bookstore.entity.Book;
import by.bookstore.entity.Comment;
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
import java.util.List;

@WebServlet(name = "BookCommentServlet", urlPatterns = "/bookComm")
public class BookCommentServlet extends HttpServlet {
    private final FacadeService facadeService = new FacadeService();
    private final Logger logger = LoggerFactory.getLogger(BookCommentServlet.class.getSimpleName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long bookId = Long.parseLong(req.getParameter("bookId"));

        Connection connection = (Connection) req.getSession().getAttribute("connection");

        List<Comment> list = facadeService.getCommentByBookBySorted(bookId, connection);
        Book book = facadeService.getBookById(bookId, connection);;
        req.setAttribute("book", book);
        req.setAttribute("listCommentsByBookId", list);
        logger.info("Request for all book ({}, {}) comments.", book.getName(), book.getAuthor());
        getServletContext().getRequestDispatcher("/comment/bookComments.jsp").forward(req, resp);
    }
}
