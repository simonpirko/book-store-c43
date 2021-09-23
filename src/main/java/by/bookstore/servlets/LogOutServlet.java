package by.bookstore.servlets;

import by.bookstore.dao.BookDAO;
import by.bookstore.dao.JDBCBookDAOImpl;
import by.bookstore.entity.Book;
import by.bookstore.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet
public class
LogOutServlet extends HttpServlet {
    private final BookDAO bookDAO = new JDBCBookDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = (List<Book>) req.getSession().getAttribute("");
        books.forEach(b -> bookDAO.updateBookReservedStatus(b.getId(), false));
        req.getSession().invalidate();
        resp.sendRedirect("/");
    }
}
