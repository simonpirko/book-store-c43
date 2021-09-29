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

@WebServlet(name = "UpdateCommentServlet", urlPatterns = "/updComm")
public class UpdateCommentServlet extends HttpServlet {
    private final FacadeService facadeService = new FacadeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("").forward(req, resp);
    }
//
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long bookId = Long.parseLong(req.getParameter("bookId"));
        String message = req.getParameter("message");

        User user = (User) req.getSession().getAttribute("user");
        if(facadeService.updateComment(new Comment(user, message, new Book(bookId)))){
            req.setAttribute("message_upd_comm", "Comment changed!");
        }else {
            req.setAttribute("message`  ", "Operation failed!");
        }
        req.getServletContext().getRequestDispatcher("").forward(req, resp);
    }
}
