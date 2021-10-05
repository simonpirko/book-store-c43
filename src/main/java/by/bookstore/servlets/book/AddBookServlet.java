package by.bookstore.servlets.book;


import by.bookstore.entity.Book;
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


@WebServlet(name = "AddBookServlet", urlPatterns = "/addBook")
public class AddBookServlet extends HttpServlet {
    private final FacadeService facadeService = new FacadeService();
    private final Logger logger = LoggerFactory.getLogger(AddBookServlet.class.getSimpleName());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/book/saveBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        double rating = Double.parseDouble(req.getParameter("rating"));
        double price = Double.parseDouble(req.getParameter("price"));

        User user = (User) req.getSession().getAttribute("user");
        Connection connection = (Connection) req.getSession().getAttribute("connection");

        if (rating <= 5 && rating >= 1) {
            Book book = new Book(name, author, rating, price, false, new User(user.getId()));
            if (facadeService.addBook(book, connection)) {
                req.setAttribute("message_add_book", "Book added");
                logger.info("Add new book: {}, {}.", book.getName(), book.getAuthor());
            } else {
                req.setAttribute("message_add_book", "Book already exists!");
            }
        } else {
            req.setAttribute("message_add_book", "Score should be between 1 and 5!");
        }
        getServletContext().getRequestDispatcher("/book/saveBook.jsp").forward(req, resp);
    }
}
