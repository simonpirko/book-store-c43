package by.bookstore.servlets;

import by.bookstore.entity.Book;
import by.bookstore.entity.Comment;
import by.bookstore.entity.User;
import by.bookstore.service.FacadeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet()
public class AddCommentServlet extends HttpServlet {
    private FacadeService facadeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("description");
        long bookId = Long.parseLong(req.getParameter("bookId"));
        User user = (User) req.getSession().getAttribute("user");
        LocalDateTime localDateTime = java.time.LocalDateTime.now();
        Book book = new Book(bookId);
        Comment comment = new Comment(localDateTime, user, description, book);
        if (facadeService.addComment(comment)) {
            req.getSession().setAttribute("message_add_com", "ok");
        } else {
            req.getSession().setAttribute("message_add_com", "error");
        }
        getServletContext().getRequestDispatcher("").forward(req, resp);


    }
}